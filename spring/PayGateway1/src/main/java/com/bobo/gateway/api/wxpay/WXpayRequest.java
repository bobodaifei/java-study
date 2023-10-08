package com.bobo.gateway.api.wxpay;

import com.bobo.gateway.utils.alipay.AlipayHashMap;
import com.bobo.gateway.utils.wxpay.WXpayConstants;

import java.util.Map;

public class WXpayRequest {

  private String nonceStr;//随机字符串

  private String notifyUrl;//通知地址

  private String body;//商品描述

  private String outTradeNo;//商户订单号

  private String totalFee;//总金额

  private String spbillCreateIp;//终端IP


  public String getNonceStr() {
    return nonceStr;
  }

  public void setNonceStr(String nonceStr) {
    this.nonceStr = nonceStr;
  }

  public String getNotifyUrl() {
    return notifyUrl;
  }

  public void setNotifyUrl(String notifyUrl) {
    this.notifyUrl = notifyUrl;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public String getOutTradeNo() {
    return outTradeNo;
  }

  public void setOutTradeNo(String outTradeNo) {
    this.outTradeNo = outTradeNo;
  }

  public String getTotalFee() {
    return totalFee;
  }

  public void setTotalFee(String totalFee) {
    this.totalFee = totalFee;
  }

  public String getSpbillCreateIp() {
    return spbillCreateIp;
  }

  public void setSpbillCreateIp(String spbillCreateIp) {
    this.spbillCreateIp = spbillCreateIp;
  }

  public Map<String, String> getTextParams() {
    AlipayHashMap txtParams = new AlipayHashMap();
    txtParams.put(WXpayConstants.NONCE_STR, this.nonceStr);
    txtParams.put(WXpayConstants.NOTIFY_URL, this.notifyUrl);
    txtParams.put(WXpayConstants.BODY, this.body);
    txtParams.put(WXpayConstants.OUT_TRADE_NO, this.outTradeNo);
    txtParams.put(WXpayConstants.TOTAL_FEE, this.totalFee);
    txtParams.put(WXpayConstants.SPBILL_CREATE_IP, this.spbillCreateIp);
    return txtParams;
  }

}
