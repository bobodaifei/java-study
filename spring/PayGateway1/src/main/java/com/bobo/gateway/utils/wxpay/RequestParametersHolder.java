package com.bobo.gateway.utils.wxpay;

public class RequestParametersHolder {
  //必填参数
  private WXpayHashMap protocalMustParams;

  //业务参数
  private WXpayHashMap applicationParams;

  public WXpayHashMap getProtocalMustParams() {
    return protocalMustParams;
  }

  public void setProtocalMustParams(WXpayHashMap protocalMustParams) {
    this.protocalMustParams = protocalMustParams;
  }

  public WXpayHashMap getApplicationParams() {
    return applicationParams;
  }

  public void setApplicationParams(WXpayHashMap applicationParams) {
    this.applicationParams = applicationParams;
  }
}