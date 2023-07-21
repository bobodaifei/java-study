package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mq")
public class ActivemqController {

  @Autowired
  private JmsMessagingTemplate jmsMessagingTemplate;

  @GetMapping("/mq01/{id}")
  @ResponseBody
  public void activetest01(@PathVariable String id){
    System.out.println("准备干活");
    jmsMessagingTemplate.convertAndSend("order.queue.id",id);
  }

  @GetMapping("mq02")
  @ResponseBody
  public String domessage(){
    String id = jmsMessagingTemplate.receiveAndConvert("order.queue.id",String.class);
    System.out.println("开始干活");
    return id;
  }

}
