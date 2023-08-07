package com.example.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
//@RefreshScope//<----添加该注解
@SpringBootApplication
public class ConfigClientMain3366 {
  public static void main(String[] args) {
    SpringApplication.run(ConfigClientMain3366.class,args);
  }
}
