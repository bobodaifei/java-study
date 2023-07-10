package com.bobo.gateway.pojo.dto;

import com.bobo.gateway.utils.JedisConstants;

import java.io.Serializable;

public class WXpayDTO implements Serializable {

  private String out_trade_no;

  private String body;

  private String notify_url;

  private String redirect_url;

  private int total_fee;

  private String spbill_create_ip;


  public String getOut_trade_no() {
    return out_trade_no;
  }

  public String getRedisNo() {
    return JedisConstants.wxpayRedisKey(out_trade_no);
  }

  public void setOut_trade_no(String out_trade_no) {
    this.out_trade_no = out_trade_no;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public String getNotify_url() {
    return notify_url;
  }

  public void setNotify_url(String notify_url) {
    this.notify_url = notify_url;
  }

  public String getRedirect_url() {
    return redirect_url;
  }

  public void setRedirect_url(String redirect_url) {
    this.redirect_url = redirect_url;
  }

  public int getTotal_fee() {
    return total_fee;
  }

  public void setTotal_fee(int total_fee) {
    this.total_fee = total_fee;
  }

  public String getSpbill_create_ip() {
    return spbill_create_ip;
  }

  public void setSpbill_create_ip(String spbill_create_ip) {
    this.spbill_create_ip = spbill_create_ip;
  }

  @Override
  public String toString() {
    return "WXpayDTO{" +
            "out_trade_no='" + out_trade_no + '\'' +
            ", body='" + body + '\'' +
            ", notify_url='" + notify_url + '\'' +
            ", redirect_url='" + redirect_url + '\'' +
            ", total_fee=" + total_fee +
            ", spbill_create_ip='" + spbill_create_ip + '\'' +
            '}';
  }
}
