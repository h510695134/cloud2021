package com.wanghh.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * direct模式 配置方式
 **/
@Configuration
public class RabbitmqDirectConfig {

    // 1,创建交换机
    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("direct_order_exchange");
    }
    // 2,创建队列
    @Bean
    public Queue smsQueue(){
        Map<String, Object> args = new HashMap<>();
        args.put("x-message-ttl",5000);//设置过期时间
        args.put("x-dead-letter-exchange","dead_direct_exchange");//设置死信队列
        args.put("x-dead-letter-routing-key","dead");
        return new Queue("direct_sms_queue",true,false,false,args);
    }
    // 3,将队列和交换机进行绑定
    @Bean
    public Binding smsBinding(){
        return BindingBuilder.bind(smsQueue()).to(directExchange()).with("direct_sms_routekey");
    }
}
