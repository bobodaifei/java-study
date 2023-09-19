package com.bobo.gateway.api.wxpay;

import cn.hutool.crypto.digest.DigestUtil;

public class WXpaySigner {

  private String appKey;


  public WXpaySigner(String appKey) {
    this.appKey = appKey;
  }

  public String sign(String content) {
    return DigestUtil.md5Hex(content).toUpperCase();
  }

  public String getAppKey() {
    return appKey;
  }

  public void setAppKey(String appKey) {
    this.appKey = appKey;
  }
}
