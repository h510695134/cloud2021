package com.wanghh.rabbitmq.listener;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @program: cloud2021
 * @description
 * @author: wanghh
 * @create: 2021-12-19 17:15
 **/
@Component
@RabbitListener(queues = {"direct_order_queue"})
public class ReceiveMessage {

    @RabbitHandler
    public void receiveMessage(String message, Channel channel, @Header(AmqpHeaders.CONSUMER_TAG) long tag) throws IOException {
        try {
            // 1,接收生产者发送的消息
            System.out.println("接收到的消息=============" + message);
            // 2,消费者进行业务处理
            System.out.println("业务处理=================");
            // 3,手动ack,通知生产者已经消费成功
            channel.basicAck(tag,false);
        }catch (Exception e){
            // 4,出现异常,进入到死信队列
            channel.basicNack(tag,false,false);
        }

    }
}
