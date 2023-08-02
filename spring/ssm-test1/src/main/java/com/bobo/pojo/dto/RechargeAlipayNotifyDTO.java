package com.bobo.pojo.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class RechargeAlipayNotifyDTO {

  private String out_trade_no;//订单编号

  private BigDecimal receipt_amount;//实付金额

  private Date gmt_payment;//支付时间

}
