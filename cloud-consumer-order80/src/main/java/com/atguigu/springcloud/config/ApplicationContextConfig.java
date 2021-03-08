package com.atguigu.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author wanghh
 * @date 2021-02-22
 */
@Configuration
public class ApplicationContextConfig {

    /**
     * 使用restTemplate实现远程调用
     * @return
     */
    @Bean
    //@LoadBalanced // @LoadBalanced 开启restTemplate负载均衡功能
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
