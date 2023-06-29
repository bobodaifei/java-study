package com.bobo.entity;

import lombok.Data;

@Data
public class WXPay {
  private String appid;
  private String mch_id;
  private String nonce_str;
  private String sign;
  private String body;
  private String out_trade_no;
  private int total_fee;
  private String spbill_create_ip;
  private String notify_url;
  private String trade_type;
  private String scene_info;
}
