package com.bobo.gateway.pojo.dto;

import com.bobo.gateway.utils.wxpay.WXpayConstants;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class WXpayXmlDTO {

  private String appid;
  private String mch_id;
  private String nonce_str;
  private String sign;
  private String result_code;
  private String trade_type;
  private String mweb_url;

  public String getAppid() {
    return appid;
  }

  public void setAppid(String appid) {
    this.appid = appid;
  }

  public String getMch_id() {
    return mch_id;
  }

  public void setMch_id(String mch_id) {
    this.mch_id = mch_id;
  }

  public String getNonce_str() {
    return nonce_str;
  }

  public void setNonce_str(String nonce_str) {
    this.nonce_str = nonce_str;
  }

  public String getSign() {
    return sign;
  }

  public void setSign(String sign) {
    this.sign = sign;
  }

  public String getResult_code() {
    return result_code;
  }

  public void setResult_code(String result_code) {
    this.result_code = result_code;
  }

  public String getTrade_type() {
    return trade_type;
  }

  public void setTrade_type(String trade_type) {
    this.trade_type = trade_type;
  }

  public String getMweb_url() {
    return mweb_url;
  }

  public void setMweb_url(String mweb_url) {
    this.mweb_url = mweb_url;
  }

  public String getPay_url(String redirect_url) {
    return mweb_url + "&" + WXpayConstants.REDIRECT_URL + "=" + redirect_url;
  }


}
