package com.example.buyticket.consumer;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DeadLetterQueueC {

  @RabbitListener(queues = "QD")
  public void receiveD(Message message, Channel channel) {
    String s = new String(message.getBody());
    log.info("延迟队列" + s);
  }

  @RabbitListener(queues = "QD")
  public void receiveXC(Message message, Channel channel) {
    String s = new String(message.getBody());
    log.info("延迟队列" + s);
  }
}
