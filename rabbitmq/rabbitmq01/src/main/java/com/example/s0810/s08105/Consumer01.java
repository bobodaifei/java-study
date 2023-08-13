package com.example.s0810.s08105;

import com.example.utils.RabbitMQUtils;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * @author frx
 * @version 1.0
 * @date 2022/7/25  17:00
 * desc:本次是为了死信队列实战
 * 消费者1
 */
public class Consumer01 {

  //普通交换机的名称
  public static final String NORMAL_EXCHANGE = "normal_exchange";
  //死信交换机的名称
  public static final String DEAD_EXCHANGE = "dead_exchange";

  //普通队列的名称
  public static final String NORMAL_QUEUE = "normal_queue";
  //死信队列的名称
  public static final String DEAD_QUEUE = "dead_queue";

  public static void main(String[] args) throws IOException, TimeoutException {

    Channel channel = RabbitMQUtils.getChannel();

    //声明死信和普通交换机，类型为direct
    channel.exchangeDeclare(NORMAL_EXCHANGE, BuiltinExchangeType.DIRECT);
    channel.exchangeDeclare(DEAD_EXCHANGE, BuiltinExchangeType.DIRECT);

    //声明普通队列
    Map<String, Object> arguments = new HashMap<>();
    //过期时间 10s 由生产者指定 更加灵活
    //arguments.put("x-message-ttl",10000);
    //正常的队列设置死信交换机
    arguments.put("x-dead-letter-exchange", DEAD_EXCHANGE);//图中红箭头
    //设置死信routingKey
    arguments.put("x-dead-letter-routing-key", "lisi");
    //设置正常队列长度的限制，例如发送10个消息，6个为正常，4个为死信
    //arguments.put("x-max-length",6);

    channel.queueDeclare(NORMAL_QUEUE, false, false, false, arguments);
    /////////////////////////////////////////////////////////////////////////
    //声明死信队列
    channel.queueDeclare(DEAD_QUEUE, false, false, false, null);

    //绑定普通的交换机与队列
    channel.queueBind(NORMAL_QUEUE, NORMAL_EXCHANGE, "zhangsan");

    //绑定死信的交换机与死信的队列
    channel.queueBind(DEAD_QUEUE, DEAD_EXCHANGE, "lisi");
    System.out.println("等待接收消息...");

    DeliverCallback deliverCallback = (consumerTag, message) -> {
      String msg = new String(message.getBody(), "UTF-8");
      if (msg.equals("info5")) {
        System.out.println("Consumer01接受的消息是：" + msg + "： 此消息是被C1拒绝的");
        //requeue 设置为 false 代表拒绝重新入队 该队列如果配置了死信交换机将发送到死信队列中
        channel.basicReject(message.getEnvelope().getDeliveryTag(), false);
      } else {
        System.out.println("Consumer01接受的消息是：" + msg);
        channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
      }

    };
    //开启自动应答，也就是关闭手动应答
    channel.basicConsume(NORMAL_QUEUE, false, deliverCallback, consumerTag -> {
    });
  }

}

