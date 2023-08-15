package com.example.dubboweb;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubboConfiguration //开启Dubbo注解配置
public class DubboWebApplication {

  public static void main(String[] args) {
    SpringApplication.run(DubboWebApplication.class, args);
  }

}
