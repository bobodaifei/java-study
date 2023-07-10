package com.bobo.sso.component.config;

import org.springframework.context.annotation.*;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan({"com.bobo.sso.service", "com.bobo.sso.utils"})
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
