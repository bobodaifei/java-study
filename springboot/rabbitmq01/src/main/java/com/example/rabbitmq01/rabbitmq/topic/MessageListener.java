package com.example.rabbitmq01.rabbitmq.topic;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

  @RabbitListener(queues = "topic_queue")
  public void receive(String id) {
    System.out.println("开始干活" + id);
  }

}
