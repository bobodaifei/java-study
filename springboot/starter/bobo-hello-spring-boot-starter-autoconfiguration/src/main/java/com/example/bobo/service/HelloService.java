package com.example.bobo.service;

import com.example.bobo.bean.HelloProperties;
import org.springframework.beans.factory.annotation.Autowired;

public class HelloService {

  @Autowired
  HelloProperties helloProperties;

  public String sayHello(String name) {
    return helloProperties.getPre() + name + helloProperties.getSuf();
  }

}
