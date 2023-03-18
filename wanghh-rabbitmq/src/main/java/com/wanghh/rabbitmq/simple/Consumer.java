package com.wanghh.rabbitmq.simple;

import com.rabbitmq.client.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @program: cloud2021
 * @description  rabbitmq 简单模式
 * @author: wanghh
 * @create: 2021-12-07 19:43
 **/
@RestController
public class Consumer {

    @GetMapping("/consumerTest")
    public void consumerTest(String[] args) throws IOException, TimeoutException {
        //所有中间件技术都是基于tcp/ip协议的基础上构建的新型协议规范,只不过rabbitmq遵循的是amqp

        //1,创建连接工程
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("47.93.215.92");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");
        connectionFactory.setVirtualHost("/");
        Connection connection = null;
        Channel channel = null;
        try {
            //2,创建连接connection
            connection = connectionFactory.newConnection("生产者");
            //3,通过连接获取通道channel
            channel = connection.createChannel();
            //4,通过创建交换机,声明队列,绑定关系,路由key,发送消息,接收消息
            channel.basicConsume("queue1", true,
                    (s, message) -> System.out.println("接收到的消息:::" + new String(message.getBody(),"UTF-8")),
                    s -> System.out.println("接收消息失败了------------"));

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //7,关闭通道
            if (channel != null && channel.isOpen()){
                channel.close();
            }
            //8,关闭连接
            if (connection != null && connection.isOpen()){
                connection.close();
            }
        }
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("");
        applicationContext.getBean("");
    }
}
