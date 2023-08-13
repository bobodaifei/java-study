package com.example.s0810.s08104;

import com.example.utils.RabbitMQUtils;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author frx
 * @version 1.0
 * @date 2022/7/24  22:31
 * desc:声明主题交换机及相关队列
 * 消费者C2
 */
public class ReceiveLogsTopic02 {

  //交换机的名称
  public static final String EXCHANGE_NAME = "topic_logs";

  //接收消息
  public static void main(String[] args) throws IOException, TimeoutException {
    Channel channel = RabbitMQUtils.getChannel();
    //声明交换机
    channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
    //声明队列
    String queueName = "Q2";
    channel.queueDeclare(queueName, false, false, false, null);
    channel.queueBind(queueName, EXCHANGE_NAME, "*.*.rabbit");
    channel.queueBind(queueName, EXCHANGE_NAME, "lazy.#");

    System.out.println("等待接收消息...");

    DeliverCallback deliverCallback = (consumerTag, message) -> {
      System.out.println(new String(message.getBody(), "UTF-8"));
      System.out.println("接收队列：" + queueName + "  绑定键:" + message.getEnvelope().getRoutingKey());
    };
    //接收消息
    channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
    });
  }
}
