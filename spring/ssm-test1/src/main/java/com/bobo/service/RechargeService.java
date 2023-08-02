package com.bobo.service;

import com.bobo.base.Result;
import com.bobo.pojo.dto.RechargeAlipayNotifyDTO;
import com.bobo.pojo.dto.RechargeDTO;
import com.bobo.pojo.dto.RechargeWXpayNotifyDTO;
import com.bobo.pojo.query.RechargeQuery;
import com.bobo.pojo.vo.RechargeVO;
import com.github.pagehelper.PageInfo;

public interface RechargeService {
  public PageInfo<RechargeVO> pageList(RechargeQuery query);

  public String placeOrder(RechargeDTO dto);

  public String toPay(RechargeDTO dto);

  public RechargeVO selectByNo(String rechargeNo);

  public void payNotify1(Result<RechargeAlipayNotifyDTO> result) throws InterruptedException;

  public void payNotify(RechargeWXpayNotifyDTO data) throws InterruptedException;
}
