package com.wanghh.rabbitmq.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @program: cloud2021
 * @description
 * @author: wanghh
 * @create: 2021-12-19 17:05
 **/
@RestController
public class OrderController {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/saveOrder")
    public void saveOrder(){
        // 1,保存订单
        String orderId = UUID.randomUUID().toString();
        // 2,保存成功 发送消息
        String exchangeName = "direct_order_exchange";
        String routingKey = "direct_sms_routekey";
        rabbitTemplate.convertAndSend(exchangeName,routingKey,"direct模式,sms发送消息==>" + orderId);
    }
}
