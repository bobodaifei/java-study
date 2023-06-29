package com.bobo.entity;

import lombok.Data;

@Data
public class AliPay {
  private String subject;// 商品名称
  private String out_trade_no;//订单编号
  private String total_amount;//应付金额
  private String product_code;//支付场景
  private String return_url;
  private String notify_url;
  private String quit_url;
}
