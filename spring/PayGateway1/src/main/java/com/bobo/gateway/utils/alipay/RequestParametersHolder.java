package com.bobo.gateway.utils.alipay;

public class RequestParametersHolder {
  //必填参数
  private AlipayHashMap protocalMustParams;
  //选填参数
  private AlipayHashMap protocalOptParams;
  //业务参数
  private AlipayHashMap applicationParams;

  public AlipayHashMap getProtocalMustParams() {
    return protocalMustParams;
  }

  public void setProtocalMustParams(AlipayHashMap protocalMustParams) {
    this.protocalMustParams = protocalMustParams;
  }

  public AlipayHashMap getProtocalOptParams() {
    return protocalOptParams;
  }

  public void setProtocalOptParams(AlipayHashMap protocalOptParams) {
    this.protocalOptParams = protocalOptParams;
  }

  public AlipayHashMap getApplicationParams() {
    return applicationParams;
  }

  public void setApplicationParams(AlipayHashMap applicationParams) {
    this.applicationParams = applicationParams;
  }
}