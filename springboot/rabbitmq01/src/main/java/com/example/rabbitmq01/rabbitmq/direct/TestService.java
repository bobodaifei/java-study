package com.example.rabbitmq01.rabbitmq.direct;

import org.springframework.amqp.core.AmqpTemplate;

//@Service
public class TestService implements MessageSevice {

//  @Autowired
  private AmqpTemplate amqpTemplate;

  @Override
  public void sendMessage(String id) {
    System.out.println("准备干活" + id);
    amqpTemplate.convertAndSend("directExchange", "direct", id);
  }

  @Override
  public String doMessage() {
    return null;
  }
}
