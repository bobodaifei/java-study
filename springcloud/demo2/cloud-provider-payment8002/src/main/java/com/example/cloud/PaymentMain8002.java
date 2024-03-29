package com.example.cloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@MapperScan("com.example.cloud.mapper")
@EnableEurekaClient//<-----添加该注解
public class PaymentMain8002 {
  public static void main(String[] args) {
    SpringApplication.run(PaymentMain8002.class,args);
  }
}