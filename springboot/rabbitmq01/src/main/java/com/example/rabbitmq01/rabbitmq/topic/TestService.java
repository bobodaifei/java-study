package com.example.rabbitmq01.rabbitmq.topic;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService implements MessageSevice {

  @Autowired
  private AmqpTemplate amqpTemplate;

  @Override
  public void sendMessage(String id) {
    System.out.println("准备干活" + id);
    amqpTemplate.convertAndSend("topicExchange", "topic.*.id", id);
  }

  @Override
  public String doMessage() {
    return null;
  }
}
