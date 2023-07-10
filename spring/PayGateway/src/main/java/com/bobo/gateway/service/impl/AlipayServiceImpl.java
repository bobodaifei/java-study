package com.bobo.gateway.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bobo.gateway.api.alipay.AlipayConfig;
import com.bobo.gateway.api.alipay.AlipayRequest;
import com.bobo.gateway.api.alipay.AlipayResponse;
import com.bobo.gateway.api.alipay.DefaultAlipayClient;
import com.bobo.gateway.base.Result;
import com.bobo.gateway.component.convert.AlipayConvert;
import com.bobo.gateway.entity.Alipay;
import com.bobo.gateway.exception.CustomException;
import com.bobo.gateway.mapper.AlipayMapper;
import com.bobo.gateway.pojo.dto.AlipayDTO;
import com.bobo.gateway.pojo.vo.AlipayVO;
import com.bobo.gateway.service.AlipayService;
import com.bobo.gateway.utils.JedisConstants;
import com.bobo.gateway.utils.JedisUtil;
import com.bobo.gateway.utils.alipay.AlipayCommonUtil;
import com.bobo.gateway.utils.alipay.AlipaySignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Map;

@Service
public class AlipayServiceImpl implements AlipayService {

  private final AlipayConvert INSTANCE = AlipayConvert.INSTANCE;

  @Autowired
  AlipayConfig config;

  @Autowired
  AlipayMapper alipayMapper;

  @Autowired
  JedisUtil jedisUtil;

  @Autowired
  RestTemplate restTemplate;

  private static final String PRODUCT_CODE = "FAST_INSTANT_TRADE_PAY";


  @Override
  @Transactional(rollbackFor = Exception.class)
  public String pay(AlipayDTO dto) throws CustomException {
//    dto.setProduct_code(PRODUCT_CODE);
    //生成表单前插入数据库
    insert(dto);
    //存入jedis ,用于支付宝回调后再回调用户系统
    jedisUtil.hset(dto.getRedisNo(), dto.getTotal_amount().setScale(2).toString(), dto.getNotify_url());

    //狸猫换太子
    dto.setNotify_url(config.getNotifyUrl());
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("subject", dto.getSubject());
    jsonObject.put("out_trade_no", dto.getOut_trade_no());
    jsonObject.put("total_amount", dto.getTotal_amount());
    jsonObject.put("quit_url", dto.getQuit_url());

    String bizContent = jsonObject.toJSONString();

    AlipayRequest request = new AlipayRequest();
    request.setReturnUrl(dto.getReturn_url());
    request.setNotifyUrl(dto.getNotify_url());
    request.setBizContent(bizContent);

    AlipayResponse response = new DefaultAlipayClient(config).pageExecute(request);

    if (response.isSuccess()) {
      System.out.println("生成表单成功");
      return response.getBody();
    }
    System.out.println("生成表单失败");
    return null;
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public String payNotify(Map<String, String[]> requestParams) throws CustomException {
    Map<String, String> params = AlipayCommonUtil.arryMapToMap(requestParams);
    if (AlipaySignature.rsaCheckV2(params, config.getAlipayPublicKey(), config.getCharset())) {
      String out_trade_no = params.get("out_trade_no");
      String total_amount = params.get("total_amount");
      String app_id = params.get("app_id");
      String seller_id = params.get("seller_id");
      String trade_status = params.get("trade_status");
      // 商家需要验证该通知数据中的 out_trade_no 是否为商家系统中创建的订单号。
      // 判断 total_amount 是否确实为该订单的实际金额（即商家订单创建时的金额）。
      //校验通知中的 seller_id（或者 seller_email) 是否为 out_trade_no 这笔单据的对应的操作方（有的时候，一个商家可能有多个 seller_id/seller_email）。
      //验证 app_id 是否为该商家本身。
      String notifyUrl;
      if (!(StrUtil.isNotEmpty(notifyUrl = jedisUtil.hget(JedisConstants.alpayRedisKey(out_trade_no), total_amount)) && config.getPid().equals(seller_id) && config.getAppId().equals(app_id))) {
        System.out.println("非本系统订单");
        return "failure";
      }
      Alipay alipay = new Alipay();
      if ("TRADE_SUCCESS".equals(trade_status) || "TRADE_FINISHED".equals(trade_status)) {
        alipay.setOutTradeNo(out_trade_no);
        alipay.setTradeNo(params.get("trade_no"));
        alipay.setAppId(app_id);
        alipay.setBuyerId(params.get("buyer_id"));
        alipay.setSellerId(seller_id);
        alipay.setTradeStatus(trade_status);
        alipay.setGmtCreate(DateUtil.parse(params.get("gmt_create")));
        alipay.setReceiptAmount(new BigDecimal(params.get("receipt_amount")));
        alipay.setGmtPayment(DateUtil.parse(params.get("gmt_payment")));

        if (alipayMapper.update(alipay) == 0) {
          return "failure";
        }
        System.out.println("支付成功，且异步回调修改数据库成功");
      }
      AlipayVO alipayVO = new AlipayVO();
      alipayVO.setOut_trade_no(out_trade_no);
      alipayVO.setReceipt_amount(new BigDecimal(params.get("receipt_amount")));
      alipayVO.setGmt_payment(DateUtil.parse(params.get("gmt_payment")));
      String data = JSON.toJSONString(Result.success(alipayVO));

      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);
      HttpEntity<String> requestEntity = new HttpEntity<>(data, headers);
      //给系统进行异步回调
      try {
        restTemplate.postForObject(notifyUrl, requestEntity, String.class);
      } catch (RestClientException e) {
        return "success";
      }
      System.out.println("回调成功，删除redis");
      jedisUtil.hdel(alipay.getRedisNo(), total_amount);
      return "success";
    }
    return "failure";
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public int insert(AlipayDTO dto) {
    Alipay entity = INSTANCE.toEntity(dto);
    return alipayMapper.insert(entity);
  }


}
