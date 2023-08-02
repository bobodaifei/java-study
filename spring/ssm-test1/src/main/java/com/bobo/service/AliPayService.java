package com.bobo.service;

import com.alipay.api.AlipayApiException;
import com.bobo.pojo.dto.AliPayDTO;

public interface AliPayService {

  public String pay(AliPayDTO dto) throws AlipayApiException;

}
