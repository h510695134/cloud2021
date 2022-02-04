package com.wanghh.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: cloud2021
 * @description
 * @author: wanghh
 * @create: 2021-12-19 17:37
 **/
@Configuration
public class RabbitmqDeadConfig {

    @Bean
    public DirectExchange deadDirectExchange(){
        return new DirectExchange("dead_direct_exchange");
    }

    @Bean
    public Queue deadQueue(){
        return new Queue("dead_direct_queue");
    }

    @Bean
    public Binding deadBinding(){
        return BindingBuilder.bind(deadQueue()).
                to(deadDirectExchange()).with("dead");
    }
}
