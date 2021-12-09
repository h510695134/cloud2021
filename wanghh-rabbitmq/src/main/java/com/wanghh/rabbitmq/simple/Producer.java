package com.wanghh.rabbitmq.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
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
public class Producer {

    @GetMapping("/test")
    public void test() throws IOException, TimeoutException {
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
            String queueName = "queue1";
            /**
             * param1 :队列名
             * param2 :是否要持久化,即是否要存盘
             * param3 :排他性,是否是独占队列
             * param4 :是否自动删除,随着最后一个消费者消费完毕以后 是否要将队列删除
             * param5 :携带的一些附加参数
             */
            channel.queueDeclare(queueName,false,false,false,null);
            //5,准备消息内容
            String message = "hello wanghh de rabbitmq 22";
            //6,发送消息给队列
            channel.basicPublish("",queueName,null,message.getBytes());

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
    }
}
