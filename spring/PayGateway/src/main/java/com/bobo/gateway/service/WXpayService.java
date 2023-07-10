package com.bobo.gateway.service;

import com.bobo.gateway.exception.CustomException;
import com.bobo.gateway.pojo.dto.WXpayDTO;
import com.bobo.gateway.pojo.dto.WXpayXml1DTO;

public interface WXpayService {

  public String pay(WXpayDTO dto) throws CustomException;

  public String payNotify(String xmlResp) throws CustomException;

  public int insert(WXpayDTO dto);

  public int update(WXpayXml1DTO dto);
}
