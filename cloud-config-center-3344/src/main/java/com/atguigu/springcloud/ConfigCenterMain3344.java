package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * spring cloud config server配置中心
 * @program: cloud2021
 * @author: wanghh
 * @create: 2021-03-22 21:46
 **/
@SpringBootApplication
@EnableConfigServer  // 激活配置中心
public class ConfigCenterMain3344 {

    public static void main(String[] args) {
        SpringApplication.run(ConfigCenterMain3344.class,args);
    }
}
