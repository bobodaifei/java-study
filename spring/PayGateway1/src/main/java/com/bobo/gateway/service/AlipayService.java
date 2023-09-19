package com.bobo.gateway.service;

import com.bobo.gateway.exception.CustomException;
import com.bobo.gateway.pojo.dto.AlipayDTO;

import java.util.Map;


/**
 * <p>
 * 服务类
 * </p>
 *
 * @author bobodaifei
 * @since 2023-06-30
 */
public interface AlipayService {

  public String pay(AlipayDTO dto) throws CustomException;

  public String payNotify(Map<String, String[]> requestParams) throws CustomException;

  public int insert(AlipayDTO dto);

}
