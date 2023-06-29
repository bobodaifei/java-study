package com.bobo.service;

import com.alipay.api.AlipayApiException;
import com.bobo.pojo.dto.AliPayDTO;

import java.util.Map;

public interface AliPayService {

  public String pay(AliPayDTO dto) throws AlipayApiException;

  public String payNotify(Map<String, String[]> requestParams);

  public void checkPayStatus(String orderNo) throws AlipayApiException;
}
