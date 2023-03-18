package com.atguigu.springcloud;

import com.atguigu.springcloud.entities.AliPayDTO;
import com.atguigu.springcloud.entities.AliPayRefundDTO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

/**
 * @program: cloud2021
 * @description
 * @author: wanghh
 * @create: 2021-03-12 21:50
 **/
@SpringBootApplication
@EnableFeignClients // open feign调用
@EnableHystrix // 配置降级
public class OrderHystrixMain80 {

    public static void main(String[] args) {
        SpringApplication.run(OrderHystrixMain80.class,args);
    }
}
