package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @program: cloud2021
 * @description
 * @author: wanghh
 * @create: 2021-03-12 21:54
 **/
@RestController
@Slf4j
@DefaultProperties(defaultFallback = "paymentGlobalFallBackMethod")
public class OrderHystrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_ok(@PathVariable Integer id){
        return paymentHystrixService.paymentInfo_ok(id);
    }

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    @HystrixCommand(fallbackMethod = "paymentTimeoutFallBackMethod",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1500")})
    public String paymentInfo_timeout(@PathVariable Integer id){
        return paymentHystrixService.paymentInfo_timeout(id);
    }
    /**
     * 降级方法
     */
    public String paymentTimeoutFallBackMethod(){
        return "我是消费者80,对方支付系统繁忙请10s后再试或自己运行出错请检查自己,o(╥﹏╥)o";
    }

    public String paymentGlobalFallBackMethod(){
        return "Global异常处理信息,请稍后重试,o(╥﹏╥)o";
    }
}
