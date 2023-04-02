package com.wanghh.kafka.controller;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;

/**
 * @program: cloud2021
 * @description kafka异步发送
 * @author: wanghh
 * @create: 2023-03-26 10:54
 **/
@RequestMapping("kafka")
@RestController
public class KafkaAsyncSendController {

    @Resource
    private KafkaTemplate kafkaTemplate;

    @GetMapping("/asyncSend")
    public String asyncSend(String message) throws ExecutionException, InterruptedException {
        ListenableFuture<SendResult<Integer,String>> future = kafkaTemplate.send("wanghh_topic1", "hello sync kafka");
        future.addCallback(new ListenableFutureCallback<SendResult<Integer, String>>() {
            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("发送消息失败="+throwable.getMessage());
            }
            @Override
            public void onSuccess(SendResult<Integer, String> result) {
                RecordMetadata metadata = result.getRecordMetadata();
                System.out.println("消息发送---->发送主题" + metadata.topic() + "/t" + metadata.partition() + "/t" +metadata.offset());
            }
        });
        return "success";
    }
}
