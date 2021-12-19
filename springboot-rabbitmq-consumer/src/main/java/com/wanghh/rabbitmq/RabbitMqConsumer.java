package com.wanghh.rabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @program: cloud2021
 * @description
 * @author: wanghh
 * @create: 2021-12-19 17:11
 **/
@SpringBootApplication
public class RabbitMqConsumer {
    public static void main(String[] args) {
        SpringApplication.run(RabbitMqConsumer.class,args);
    }
}
