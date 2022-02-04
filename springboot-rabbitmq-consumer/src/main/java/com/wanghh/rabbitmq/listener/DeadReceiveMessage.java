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
        // 1,死信队列接收消息
        System.out.println("消费者消费异常进入死信队列,接收到的消息=============" + message);
        // 2,此条消息消费异常,可进行发短信提醒,人工进行特殊处理
        System.out.println("发送短信提醒,订单异常,message = " + message);
    }
}
