package com.example.rabbitmq01.controller;

import com.example.rabbitmq01.rabbitmq.topic.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mq")
public class ActivemqController {

  @Autowired
  private TestService testService;

  @GetMapping("/mq01/{id}")
  @ResponseBody
  public void activetest01(@PathVariable String id){
    System.out.println("准备干活");
    testService.sendMessage(id);
  }

}
