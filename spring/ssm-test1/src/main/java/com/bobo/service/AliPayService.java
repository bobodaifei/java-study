package com.bobo.service;

import com.alipay.api.AlipayApiException;
import com.bobo.base.Result;
import com.bobo.pojo.dto.AliPayDTO;
import com.bobo.pojo.dto.AlipayNotifyDTO;

import java.util.Map;

public interface AliPayService {

  public String pay(AliPayDTO dto) throws AlipayApiException;

  public String payNotify(Map<String, String[]> requestParams);

  public void checkPayStatus(String orderNo) throws AlipayApiException;

  public void payNotify1(Result<AlipayNotifyDTO> result);
}
