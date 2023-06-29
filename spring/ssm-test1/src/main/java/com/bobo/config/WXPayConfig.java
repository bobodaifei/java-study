package com.bobo.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Data
@Configuration
@PropertySource({"classpath:wxpay.properties"})
public class WXPayConfig {

  @Value("${wxpay.appid}")
  private String appid;
  @Value("${wxpay.mch_id}")
  private String mch_id;
  @Value("${wxpay.notify_url}")
  private String notify_url;
  @Value("${wxpay.return_url}")
  private String return_url;
  @Value("${wxpay.trade_type}")
  private String trade_type;
  @Value("${wxpay.scene_info}")
  private String scene_info;
  @Value("${wxpay.sign_type}")
  private String sign_type;
  @Value("${wxpay.Appkey}")
  private String Appkey;

}
