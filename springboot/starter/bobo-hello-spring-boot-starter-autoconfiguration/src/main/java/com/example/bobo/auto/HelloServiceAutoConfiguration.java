package com.example.bobo.auto;

import com.example.bobo.bean.HelloProperties;
import com.example.bobo.service.HelloService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(HelloProperties.class)
public class HelloServiceAutoConfiguration {

  @ConditionalOnMissingBean(HelloService.class)
  @Bean
  public HelloService helloService(){
    return new HelloService();
  }



}
