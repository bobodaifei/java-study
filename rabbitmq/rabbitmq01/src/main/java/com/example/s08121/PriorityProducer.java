package com.example.s08121;

import com.example.utils.RabbitMQUtils;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author frx
 * @version 1.0
 * @date 2022/7/26  23:53
 * desc:优先级 生产者
 */
public class PriorityProducer {

  private static final String QUEUE_NAME = "queue1";

  public static void main(String[] args) throws IOException, TimeoutException {
    Channel channel = RabbitMQUtils.getChannel();

    //给消息赋予一个priority属性
    AMQP.BasicProperties properties =
            new AMQP.BasicProperties().builder().priority(1).priority(10).build();

    for (int i = 1; i < 11; i++) {
      String message = "info" + i;
      if (i == 5) {
        channel.basicPublish("", QUEUE_NAME, properties, message.getBytes());
      } else {
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
      }
      System.out.println("消息发送完成：" + message);
    }
  }
}
