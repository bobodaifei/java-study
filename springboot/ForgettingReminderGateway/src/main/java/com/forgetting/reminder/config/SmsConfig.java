package com.forgetting.reminder.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("sms")
@Data
public class SmsConfig {

  private String url;

  private String sendKey;

  private String orgCode;

  private String checkCode;

  private String sendName;



}
