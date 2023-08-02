package com.bobo.pojo.dto;

import lombok.Data;

@Data
public class RechargeWXpayNotifyDTO {

  private String body;

  private String out_trade_no;

  private int total_fee;

  private String spbill_create_ip;

}
