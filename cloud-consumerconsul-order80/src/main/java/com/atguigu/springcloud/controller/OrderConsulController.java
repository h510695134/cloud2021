package com.atguigu.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author wanghh
 * @date 2021-03-05
 */
@RestController
@Slf4j
public class OrderConsulController {
    public static final String INVOKE_RUL = "http://consul-provider-payment";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/consul")
    public String paymentInfo(){
        return restTemplate.getForObject(INVOKE_RUL + "/payment/consul", String.class);
    }
}
