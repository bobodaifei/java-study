package com.example.s0809.s891;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author frx
 * @version 1.0
 * @date 2022/7/23  22:21
 * desc:消费者：接受消息
 */
public class Consumer {

  //队列的名称
  public static final String QUEUE_NAME = "hello";

  //接受消息
  public static void main(String[] args) throws IOException, TimeoutException {
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("127.0.0.1");
    factory.setUsername("admin");
    factory.setPassword("123456");
    Connection connection = factory.newConnection();

    Channel channel = connection.createChannel();

    //声明接收消息
    DeliverCallback deliverCallback = (consumerTag, message) -> {
      System.out.println(new String(message.getBody()));
    };
    //取消消息时的回调
    CancelCallback cancelCallback = consumerTag -> {
      System.out.println("消息消费被中断");
    };

    /**
     * 消费者消费消息
     * 1.消费哪个队列
     * 2.消费成功之后是否要自动应答true：代表自动应答false:代表手动应答
     * 3.消费者未成功消费的回调
     * 4.消费者取消消费的回调
     */
    channel.basicConsume(QUEUE_NAME, true, deliverCallback, cancelCallback);
  }
}