package com.bobo.config;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayConfig;
import com.alipay.api.DefaultAlipayClient;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Data
@Configuration
@PropertySource({"classpath:alipay.properties"})
public class AliPayConfig {

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

  @Value("${alipay.payUrl}")
  private String payUrl;


  @Bean
  public AlipayConfig alipayConfig() {
    AlipayConfig config = new AlipayConfig();
    config.setServerUrl(serverUrl);
    config.setAppId(appId);
    config.setFormat(format);
    config.setCharset(charset);
    config.setSignType(signType);
    config.setPrivateKey(privateKey);
    config.setAlipayPublicKey(alipayPublicKey);
    return config;
  }

  @Bean
  public AlipayClient alipayClient() throws AlipayApiException {
    return new DefaultAlipayClient(alipayConfig());
  }

}
