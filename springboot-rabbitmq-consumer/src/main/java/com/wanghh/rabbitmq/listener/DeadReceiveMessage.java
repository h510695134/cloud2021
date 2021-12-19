package com.wanghh.rabbitmq.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @program: cloud2021
 * @description
 * @author: wanghh
 * @create: 2021-12-19 17:15
 **/
@Component
@RabbitListener(queues = {"dead_direct_queue"})
public class DeadReceiveMessage {

    @RabbitHandler
    public void receiveMessage(String message){
        System.out.println("死信队列,接收到的消息=============" + message);
    }
}
