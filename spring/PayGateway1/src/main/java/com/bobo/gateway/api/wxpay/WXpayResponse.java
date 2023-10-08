package com.bobo.gateway.api.wxpay;

public class WXpayResponse {
  private String code;
  private String msg;
  private String body;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public boolean isSuccess() {
    return !"-1".equals(code);
  }
}
