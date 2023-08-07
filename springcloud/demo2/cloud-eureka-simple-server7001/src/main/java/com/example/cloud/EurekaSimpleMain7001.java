package com.example.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
//注册中心
@EnableEurekaServer
public class EurekaSimpleMain7001 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaSimpleMain7001.class,args);
    }
}