package com.example.bobo.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("bobo.hello")
public class HelloProperties {

  private String pre;

  private String suf;

  public String getPre() {
    return pre;
  }

  public void setPre(String pre) {
    this.pre = pre;
  }

  public String getSuf() {
    return suf;
  }

  public void setSuf(String suf) {
    this.suf = suf;
  }
}
