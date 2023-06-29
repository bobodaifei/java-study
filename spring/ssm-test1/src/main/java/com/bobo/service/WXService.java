package com.bobo.service;

import com.bobo.pojo.dto.WXPayDTO;
import com.bobo.pojo.vo.WXPayVO;

public interface WXService {
  public String pay(WXPayDTO dto);

  public  void payNotify(WXPayVO vo);
}
