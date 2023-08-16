package com.bobo.gateway.api.alipay;

import com.bobo.gateway.exception.CustomException;
import com.bobo.gateway.utils.alipay.AlipaySignUtils;

//加签类
public class AlipaySigner {

  private String privateKey;

  public AlipaySigner(String privateKey) {
    this.privateKey = privateKey;
  }

  public String sign(String content, String charset) throws CustomException {
    return AlipaySignUtils.rsa2Sign(content, this.privateKey, charset);
  }

  public String base64Sign(String content, String charset) throws CustomException {
    return AlipaySignUtils.rsa2Sign(content, this.privateKey, charset);
  }

  public String getPrivateKey() {
    return privateKey;
  }

  public void setPrivateKey(String privateKey) {
    this.privateKey = privateKey;
  }

}
