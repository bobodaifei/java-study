package com.example.rabbitmq01.rabbitmq.direct;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;

//@Configuration
public class Config {

  //消息队列
  @Bean
  public Queue directQueue() {
    //后续三个参数
    //是否持久化
    //连接专用
    //是否删除
    return new Queue("direct_queue");
  }

  @Bean
  public Queue directQueue1() {
    //后续三个参数
    //是否持久化
    //连接专用
    //是否删除
    return new Queue("direct_queue1");
  }

  //交换机
  @Bean
  public DirectExchange directExchange() {
    return new DirectExchange("directExchange");
  }

  //绑定交换机和队列
  @Bean
  public Binding bindingDirect() {
    return BindingBuilder
            .bind(directQueue()).
            to(directExchange())
            .with("direct");
  }

  @Bean
  public Binding bindingDirect1() {
    return BindingBuilder
            .bind(directQueue1()).
            to(directExchange())
            .with("direct1");
  }

}
