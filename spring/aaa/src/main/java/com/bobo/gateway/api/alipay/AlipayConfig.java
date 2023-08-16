package com.bobo.gateway.api.alipay;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

//一般不做修改的配置文件
@Configuration
@PropertySource({"classpath:alipay.properties"})
public class AlipayConfig {

  @Value("${alipay.open_api_domain}")
  private String serverUrl;

  @Value("${alipay.appId}")
  private String appId;

  @Value("${alipay.format}")
  private String format;

  @Value("${alipay.charset}")
  private String charset;

  @Value("${alipay.sign_type}")
  private String signType;

  @Value("${alipay.private_key}")
  private String privateKey;

  @Value("${alipay.alipay_public_key}")
  private String alipayPublicKey;

  @Value("${alipay.notify_url}")
  private String notifyUrl;

  @Value("${alipay.pid}")
  private String pid;

  public String getServerUrl() {
    return serverUrl;
  }

  public void setServerUrl(String serverUrl) {
    this.serverUrl = serverUrl;
  }

  public String getAppId() {
    return appId;
  }

  public void setAppId(String appId) {
    this.appId = appId;
  }

  public String getFormat() {
    return format;
  }

  public void setFormat(String format) {
    this.format = format;
  }

  public String getCharset() {
    return charset;
  }

  public void setCharset(String charset) {
    this.charset = charset;
  }

  public String getSignType() {
    return signType;
  }

  public void setSignType(String signType) {
    this.signType = signType;
  }

  public String getPrivateKey() {
    return privateKey;
  }

  public void setPrivateKey(String privateKey) {
    this.privateKey = privateKey;
  }

  public String getAlipayPublicKey() {
    return alipayPublicKey;
  }

  public void setAlipayPublicKey(String alipayPublicKey) {
    this.alipayPublicKey = alipayPublicKey;
  }

  public String getNotifyUrl() {
    return notifyUrl;
  }

  public void setNotifyUrl(String notifyUrl) {
    this.notifyUrl = notifyUrl;
  }

  public String getPid() {
    return pid;
  }

  public void setPid(String pid) {
    this.pid = pid;
  }
}
