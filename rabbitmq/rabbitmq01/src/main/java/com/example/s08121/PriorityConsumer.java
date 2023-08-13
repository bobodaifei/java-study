package com.example.s08121;

import com.example.utils.RabbitMQUtils;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * @author frx
 * @version 1.0
 * @date 2022/7/26  23:58
 * desc:优先级 消费者
 */
public class PriorityConsumer {

    private final static String QUEUE_NAME = "queue1";

    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitMQUtils.getChannel();

        //设置队列的最大优先级 最大可以设置到255 官网推荐1-10 如果设置太高比较吃内存和CPU
        Map<String, Object> params = new HashMap<>();
        params.put("x-max-priority",10);
        channel.queueDeclare(QUEUE_NAME,true,false,false,params);

        //惰性队列
//        Map<String, Object> args = new HashMap<String, Object>();
//        args.put("x-queue-mode", "lazy");
//        channel.queueDeclare("myqueue", false, false, false, args);

        //推送消息如何进行消费的接口回调
        DeliverCallback deliverCallback = (consumerTag, delivery) ->{
            String message = new String(delivery.getBody());
            System.out.println("消费的消息: "+message);
        };

        //取消消费的一个回调接口 如在消费的时候队列被删除掉了
        CancelCallback cancelCallback = (consumerTag) ->{
            System.out.println("消息消费被中断");
        };

        channel.basicConsume(QUEUE_NAME,true,deliverCallback,cancelCallback);
    }
}
