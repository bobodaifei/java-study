package com.example.cloud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OrderConsulMain82 {
  public static void main(String[] args) {
    SpringApplication.run(OrderConsulMain82.class,args);
  }
}
