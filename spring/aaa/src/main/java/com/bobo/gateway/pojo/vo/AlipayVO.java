package com.bobo.gateway.pojo.vo;

import java.math.BigDecimal;
import java.util.Date;

public class AlipayVO {

  private String out_trade_no;//订单编号

  private BigDecimal receipt_amount;//实付金额

  private Date gmt_payment;//支付时间

  public String getOut_trade_no() {
    return out_trade_no;
  }

  public void setOut_trade_no(String out_trade_no) {
    this.out_trade_no = out_trade_no;
  }

  public BigDecimal getReceipt_amount() {
    return receipt_amount;
  }

  public void setReceipt_amount(BigDecimal receipt_amount) {
    this.receipt_amount = receipt_amount;
  }

  public Date getGmt_payment() {
    return gmt_payment;
  }

  public void setGmt_payment(Date gmt_payment) {
    this.gmt_payment = gmt_payment;
  }
}
