package com.bobo.service.impl;

import com.alibaba.fastjson.JSON;
import com.bobo.base.Result;
import com.bobo.component.convert.WXPayConvert;
import com.bobo.config.WXPayConfig;
import com.bobo.entity.Order;
import com.bobo.pojo.dto.WXPayDTO;
import com.bobo.pojo.vo.WXPayVO;
import com.bobo.service.OrderService;
import com.bobo.service.WXService;
import com.bobo.utils.JedisUtil;
import com.bobo.utils.OrderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class WXServiceImpl implements WXService {

  private final WXPayConvert INSTANCE = WXPayConvert.INSTANCE;

  @Autowired
  RestTemplate restTemplate;

  @Autowired
  WXPayConfig wxPayConfig;

  @Autowired
  OrderService orderService;

  @Autowired
  JedisUtil jedisUtil;

  @Override
  public String pay(WXPayDTO dto) {
//    wxPay.setBody(dto.getBody());
//    wxPay.setOut_trade_no(dto.getOut_trade_no());
//    wxPay.setTotal_fee(dto.getTotal_fee());
//    SortedMap<String, Object> map = new TreeMap<>();
//    map.put("appid", wxPayConfig.getAppid());
//    map.put("mch_id", wxPayConfig.getMch_id());
//    map.put("nonce_str", IdUtil.simpleUUID());
//    map.put("body", dto.getBody());
//    map.put("out_trade_no", dto.getOut_trade_no());
//    map.put("total_fee", dto.getTotal_fee());
//    map.put("spbill_create_ip", dto.getSpbill_create_ip());
//    map.put("notify_url", wxPayConfig.getNotify_url());
//    map.put("trade_type", wxPayConfig.getTrade_type());
//    map.put("scene_info", wxPayConfig.getScene_info());
//    String sign = WXUtil.generatesignature(map, wxPayConfig.getAppkey());
//    map.put("sign", sign);

    String url = "http://47.94.220.67:8080/Pay/wxPays";
    Map<String, Object> map = new HashMap<>();
    map.put("subject", dto.getBody());
    map.put("out_trade_no", dto.getOut_trade_no());
    map.put("total_amount", dto.getTotal_fee());
    map.put("notify_url", wxPayConfig.getNotify_url());
    map.put("return_url", wxPayConfig.getReturn_url());

    String jsonString = JSON.toJSONString(map);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<String> requestEntity = new HttpEntity<>(jsonString, headers);
    ResponseEntity<Result> responseEntity = restTemplate.postForEntity(url, requestEntity, Result.class);
    System.out.println(responseEntity.getBody().getData());
    return responseEntity.getBody().getData().toString();
  }

  @Override
  public void payNotify(WXPayVO vo) {
    Order order = new Order();
    order.setOrderNo(vo.getOut_trade_no());
    order.setPayTime(new Date(vo.getPayTime()));
    order.setStatus(OrderUtil.STATUS4);
    order.setPayMethod("微信");
    orderService.modifyOrder(order);
  }


}
