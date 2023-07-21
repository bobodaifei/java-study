package com.example.lintener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
public class MqListenerMessage {

  @JmsListener(destination = "order.queue.id")
  @SendTo("order.queue.other.id")
  public void receive(String id){
    System.out.println("开始干活"+id);
  }


}
