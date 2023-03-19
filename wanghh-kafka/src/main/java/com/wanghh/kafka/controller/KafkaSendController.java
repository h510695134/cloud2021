package com.wanghh.kafka.controller;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @program: cloud2021
 * @description
 * @author: wanghh
 * @create: 2023-03-19 10:53
 **/
@RequestMapping("/kafkaSend")
@RestController
public class KafkaSendController {

    @Resource
    private KafkaTemplate<String,Object> kafkaTemplate;

    @GetMapping("/sendMessage")
    public void sendMessage(@RequestParam("message") String message){
        System.out.println("kafka发送信息="+message);
        kafkaTemplate.send("topic1",message);
    }
}
