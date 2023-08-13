package com.example.s0810.s08105;

import com.example.utils.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author frx
 * @version 1.0
 * @date 2022/7/25  19:30
 */
public class Consumer02 {

  //死信队列的名称
  public static final String DEAD_QUEUE = "dead_queue";

  public static void main(String[] args) throws IOException, TimeoutException {

    Channel channel = RabbitMQUtils.getChannel();

    System.out.println("等待接收死信消息...");

    DeliverCallback deliverCallback = (consumerTag, message) -> {
      System.out.println("Consumer02接受的消息是：" + new String(message.getBody(), "UTF-8"));
    };

    channel.basicConsume(DEAD_QUEUE, true, deliverCallback, consumerTag -> {
    });
  }
}
