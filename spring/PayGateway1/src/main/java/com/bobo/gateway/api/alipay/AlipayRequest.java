package com.bobo.gateway.api.alipay;

import com.bobo.gateway.utils.alipay.AlipayHashMap;

import java.util.Map;

//动态参数，可能会因为场景和请求发生变化
public class AlipayRequest {

//  private String method = "alipay.trade.page.pay";
  private String method = "alipay.trade.wap.pay";

  private String version = "1.0";

  private String returnUrl;

  private String notifyUrl;

  private String bizContent;

  public String getVersion() {
    return version;
  }

  public String getMethod() {
    return method;
  }

  public String getReturnUrl() {
    return returnUrl;
  }

  public void setReturnUrl(String returnUrl) {
    this.returnUrl = returnUrl;
  }

  public String getNotifyUrl() {
    return notifyUrl;
  }

  public void setNotifyUrl(String notifyUrl) {
    this.notifyUrl = notifyUrl;
  }

  public void setMethod(String method) {
    this.method = method;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public String getBizContent() {
    return bizContent;
  }

  public void setBizContent(String bizContent) {
    this.bizContent = bizContent;
  }

  public Map<String, String> getTextParams() {
    AlipayHashMap txtParams = new AlipayHashMap();
    txtParams.put("biz_content", this.bizContent);
    return txtParams;
  }
}
