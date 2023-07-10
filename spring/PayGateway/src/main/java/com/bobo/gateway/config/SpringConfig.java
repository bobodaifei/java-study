package com.bobo.gateway.config;

import org.springframework.context.annotation.*;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan({"com.bobo.gateway.service", "com.bobo.gateway.utils","com.bobo.gateway.api"})
//开启aop
@EnableAspectJAutoProxy
//@EnableScheduling
@Import({DataSourceConfig.class, TransactionConfig.class, MybatisConfig.class, JedisConfig.class})
public class SpringConfig {

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }
}
