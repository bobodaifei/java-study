package com.example.zookeeper02.controller;

import com.example.zookeeper02.utils.ZKRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

  @Autowired
  ZKRestTemplate zkRestTemplate;

  @RequestMapping("/test")
  public String test() {
    System.out.println("将要调用其他服务");
    return zkRestTemplate.getJson("http://cloud-consumer-order/test");
  }

}
