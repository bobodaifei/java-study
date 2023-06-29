package com.bobo.config;

import org.springframework.context.annotation.*;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan({"com.bobo.service", "com.bobo.aop", "com.bobo.utils","com.bobo.task"})
//开启aop
@EnableAspectJAutoProxy
//@EnableScheduling
@Import({DataSourceConfig.class, TransactionConfig.class, MybatisConfig.class, JedisConfig.class, AliPayConfig.class, WXPayConfig.class})
public class SpringConfig {

  @Bean
  public RestTemplate restTemplate(){
    return new RestTemplate();
  }
}
