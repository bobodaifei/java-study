package com.example.zookeeper01.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ZKRegister {

  @Autowired
  private ServiceRegistry serviceRegistry;

  @Value("${spring.application.name}")
  private String serviceName;

  @Value("${server.port}")
  private String servicePort;


  // 在服务启动时注册服务
  @PostConstruct
  public void registerService() throws Exception {
    String serviceAddress = "127.0.0.1:" + servicePort;
    serviceRegistry.registerService(serviceName, serviceAddress);
  }

}
