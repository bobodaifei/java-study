package com.bobo.gateway.service.impl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.bobo.gateway.api.wxpay.DefaultWXpayClient;
import com.bobo.gateway.api.wxpay.WXpayConfig;
import com.bobo.gateway.api.wxpay.WXpayRequest;
import com.bobo.gateway.api.wxpay.WXpayResponse;
import com.bobo.gateway.base.Result;
import com.bobo.gateway.component.convert.WXpayConvert;
import com.bobo.gateway.entity.WXpay;
import com.bobo.gateway.exception.CustomException;
import com.bobo.gateway.mapper.WXpayMapper;
import com.bobo.gateway.pojo.dto.WXpayDTO;
import com.bobo.gateway.pojo.dto.WXpayXml1DTO;
import com.bobo.gateway.pojo.dto.WXpayXmlDTO;
import com.bobo.gateway.pojo.vo.WXpayVO;
import com.bobo.gateway.service.WXpayService;
import com.bobo.gateway.utils.JedisUtil;
import com.bobo.gateway.utils.wxpay.WXpayCommonUtil;
import com.bobo.gateway.utils.wxpay.WXpayConstants;
import com.bobo.gateway.utils.wxpay.WXpaySignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class WXpayServiceImpl implements WXpayService {

  private final WXpayConvert INSTANCE = WXpayConvert.INSTANCE;

  @Autowired
  WXpayConfig config;

  @Autowired
  JedisUtil jedisUtil;

  @Autowired
  WXpayMapper wxpayMapper;

  @Autowired
  RestTemplate restTemplate;


  @Override
  public String pay(WXpayDTO dto) throws CustomException {
    //生成表单前插入数据库
    insert(dto);
    //存入jedis ,用于微信回调后再回调用户系统
    jedisUtil.hset(dto.getRedisNo(), String.valueOf(dto.getTotal_fee()), dto.getNotify_url());
    //狸猫换太子
    WXpayRequest request = new WXpayRequest();
    request.setNotifyUrl(config.getNotifyUrl());
    request.setBody(dto.getBody());
    request.setOutTradeNo(dto.getOut_trade_no());
    String randomStr = RandomUtil.randomString(32);
    request.setNonceStr(randomStr);
    request.setTotalFee(String.valueOf(dto.getTotal_fee()));
    request.setSpbillCreateIp(dto.getSpbill_create_ip());

    WXpayResponse response = new DefaultWXpayClient(config).pageExecute(request);
    if (response.isSuccess()) {
      if (!WXpaySignature.isSignatureValid(response.getBody(), config.getAppKey())) {
        System.out.println("获取链接失败，验签失败");
        return null;
      }
      WXpayXmlDTO wxpayXmlDTO = WXpayCommonUtil.xmlToEntity(response.getBody(), WXpayXmlDTO.class);
      if (!"SUCCESS".equals(wxpayXmlDTO.getResult_code())) {
        System.out.println("获取链接失败，状态失败");
        return null;
      }
      System.out.println("返回支付链接" + wxpayXmlDTO.getPay_url(dto.getRedirect_url()));
      return wxpayXmlDTO.getPay_url(dto.getRedirect_url());
    }
    System.out.println("获取链接失败");
    return null;
  }

  @Override
  public String payNotify(String xmlResp) throws CustomException {
    System.out.println("回调成功" + xmlResp);
    //验签失败
    if (!WXpaySignature.isSignatureValid(xmlResp, config.getAppKey())) {
      System.out.println("验签失败");
      return WXpayConstants.RETURN_ERROR1;
    }
    WXpayXml1DTO dto = WXpayCommonUtil.xmlToEntity(xmlResp, WXpayXml1DTO.class);
    String notifyUrl;
    //不是本系统订单
    if (!(StrUtil.isNotEmpty(notifyUrl = jedisUtil.hget(dto.getRedisNo(), dto.getTotal_fee().toString())) && config.getAppid().equals(dto.getAppid()))) {
      System.out.println("非本系统订单");
      return WXpayConstants.RETURN_ERROR2;
    }
    //成功则修改支付信息
    System.out.println("验签成功");
    int i = update(dto);
    if (i == 0) {
      System.out.println("重复回调");
      return WXpayConstants.RETURN_SUCCESS;
    }
    System.out.println("修改数据库成功");
    WXpayVO wXpayVO = new WXpayVO();
    wXpayVO.setResult_code(dto.getResult_code());
    wXpayVO.setOut_trade_no(dto.getOut_trade_no());
    wXpayVO.setSettlement_total_fee(dto.getSettlement_total_fee());
    wXpayVO.setTime_end(dto.getTime_end());
    wXpayVO.setTotal_fee(dto.getTotal_fee());
    String data = JSON.toJSONString(Result.success(wXpayVO));
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<String> requestEntity = new HttpEntity<>(data, headers);
    //给系统进行异步回调
    System.out.println("尝试给系统进行回调");
    try {
      restTemplate.postForObject(notifyUrl, requestEntity, String.class);
    } catch (RestClientException e) {
      System.out.println("回调系统失败");
      return WXpayConstants.RETURN_SUCCESS;
    }
    System.out.println("回调成功，删除redis");
    jedisUtil.hdel(dto.getRedisNo(), String.valueOf(dto.getTotal_fee()));
    return WXpayConstants.RETURN_SUCCESS;
  }

  @Override
  public int insert(WXpayDTO dto) {
    WXpay entity = INSTANCE.toEntity(dto);
    return wxpayMapper.insert(entity);
  }

  @Override
  public int update(WXpayXml1DTO dto) {
    WXpay entity = INSTANCE.toEntity(dto);
    System.out.println("转换成功");
    return wxpayMapper.update(entity);
  }


}
