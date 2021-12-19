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
//@RabbitListener(queues = {"direct_sms_queue"})
public class ReceiveMessage {

    //@RabbitHandler
    public void receiveMessage(String message){
        System.out.println("接收到的消息=============" + message);
    }
}
