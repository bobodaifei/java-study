package com.bobo.service.impl;

import com.alibaba.fastjson.JSON;
import com.bobo.base.Result;
import com.bobo.component.convert.WXPayConvert;
import com.bobo.config.WXPayConfig;
import com.bobo.pojo.dto.WXPayDTO;
import com.bobo.service.OrderService;
import com.bobo.service.WXService;
import com.bobo.utils.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
    String url = "http://m.jnbat.com:8081/PayGateway/wxpay/pay";
    Map<String, Object> map = new HashMap<>();
    map.put("body", dto.getBody());
    map.put("out_trade_no", dto.getOut_trade_no());
    map.put("total_fee", dto.getTotal_fee());
    map.put("notify_url", dto.getNotifyUrl());
    map.put("redirect_url", dto.getReturnUrl());

    String jsonString = JSON.toJSONString(map);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<String> requestEntity = new HttpEntity<>(jsonString, headers);
    ResponseEntity<Result> responseEntity = restTemplate.postForEntity(url, requestEntity, Result.class);
    System.out.println(responseEntity.getBody().getData());
    return responseEntity.getBody().getData().toString();
  }




}
