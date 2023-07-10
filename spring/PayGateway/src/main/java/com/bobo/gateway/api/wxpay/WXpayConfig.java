package com.bobo.gateway.api.wxpay;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource({"classpath:wxpay.properties"})
public class WXpayConfig {

  @Value("${wxpay.server_url}")
  private String serverUrl;//URL地址

  @Value("${wxpay.appid}")
  private String appid;//公众账号ID

  @Value("${wxpay.mch_id}")
  private String mchId;//商户号

  @Value("${wxpay.sign_type}")
  private String signType;//签名类型

  @Value("${wxpay.trade_type}")
  private String tradeType;//交易类型

  @Value("${wxpay.scene_info}")
  private String sceneInfo;//场景信息

  @Value("${wxpay.notify_url}")
  private String notifyUrl;

  @Value("${wxpay.app_key}")
  private String appKey;//密钥




  public String getServerUrl() {
    return serverUrl;
  }

  public void setServerUrl(String serverUrl) {
    this.serverUrl = serverUrl;
  }

  public String getAppid() {
    return appid;
  }

  public void setAppid(String appid) {
    this.appid = appid;
  }

  public String getMchId() {
    return mchId;
  }

  public void setMchId(String mchId) {
    this.mchId = mchId;
  }

  public String getSignType() {
    return signType;
  }

  public void setSignType(String signType) {
    this.signType = signType;
  }

  public String getTradeType() {
    return tradeType;
  }

  public void setTradeType(String tradeType) {
    this.tradeType = tradeType;
  }

  public String getSceneInfo() {
    return sceneInfo;
  }

  public void setSceneInfo(String sceneInfo) {
    this.sceneInfo = sceneInfo;
  }

  public String getNotifyUrl() {
    return notifyUrl;
  }

  public void setNotifyUrl(String notifyUrl) {
    this.notifyUrl = notifyUrl;
  }

  public String getAppKey() {
    return appKey;
  }

  public void setAppKey(String appKey) {
    this.appKey = appKey;
  }
}
