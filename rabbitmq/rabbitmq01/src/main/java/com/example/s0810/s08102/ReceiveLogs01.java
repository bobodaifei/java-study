package com.example.s0810.s08102;

import com.example.utils.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author frx
 * @version 1.0
 * @date 2022/7/24  21:20
 */
//消费者1
public class ReceiveLogs01 {

  //交换机名称
  private static final String EXCHANGE_NAME = "logs";

  public static void main(String[] args) throws IOException, TimeoutException {
    Channel channel = RabbitMQUtils.getChannel();
    //声明一个交换机
    channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
    //声明一个队列 临时队列
    /**
     * 生成一个临时的队列，队列的名称是随机的
     * 当消费者断开与队列的连接的时候 队列就自动删除
     */
    String queueName = channel.queueDeclare().getQueue();
    /**
     * 绑定交换机与队列
     */
    channel.queueBind(queueName, EXCHANGE_NAME, "");
    System.out.println("等待接收消息，把接收到的消息打印在屏幕上...");
    //接收消息
    //消费者取消消息时回调接口
    DeliverCallback deliverCallback = (consumerTag, message) -> {
      System.out.println("控制台打印接收到的消息:" + new String(message.getBody(), "UTF-8"));
    };
    channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
    });
  }
}
