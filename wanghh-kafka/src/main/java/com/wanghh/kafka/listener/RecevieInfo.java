package com.wanghh.kafka.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @program: cloud2021
 * @description
 * @author: wanghh
 * @create: 2023-03-19 13:13
 **/
@Component
public class RecevieInfo {

    @KafkaListener(topics = "topic1")
    public void recevieInfo(String message){
        System.out.println("接收消息="+message);
    }
}
