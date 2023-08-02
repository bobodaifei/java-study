package com.example.rabbitmq01.rabbitmq.direct;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

//@Component
public class MessageListener {

  @RabbitListener(queues = "direct_queue")
  public void receive(String id) {
    System.out.println("开始干活" + id);
  }

}
