package com.example.dubboservice;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubboConfiguration //开启Dubbo注解配置
public class DubboServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(DubboServiceApplication.class, args);
  }

}
