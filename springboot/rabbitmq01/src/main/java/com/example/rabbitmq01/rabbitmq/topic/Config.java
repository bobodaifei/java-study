package com.example.rabbitmq01.rabbitmq.topic;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

  //消息队列
  @Bean
  public Queue topicQueue() {
    //后续三个参数
    //是否持久化
    //连接专用
    //是否删除
    return new Queue("topic_queue");
  }

  //交换机
  @Bean
  public TopicExchange topicExchange() {
    return new TopicExchange("topicExchange");
  }

  //绑定交换机和队列
  @Bean
  public Binding bindingtopic() {
    return BindingBuilder
            .bind(topicQueue()).
            to(topicExchange())
            //模糊匹配
            .with("topic.*.id");
  }

}
