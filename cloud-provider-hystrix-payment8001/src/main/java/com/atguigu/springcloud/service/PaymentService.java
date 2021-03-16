package com.atguigu.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

/**
 * @program: cloud2021
 * @description
 * @author: wanghh
 * @create: 2021-03-10 20:32
 **/
@Service
public class PaymentService {

    public String paymentInfo_ok(Integer id){
        return "线程池:  "+Thread.currentThread().getName()+"paymentInfo_ok,id: "+id;
    }

    @HystrixCommand(fallbackMethod = "paymentInfo_timeout_handler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000")})
    public String paymentInfo_timeout(Integer id){
        int time  = 3;
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池:  "+Thread.currentThread().getName()+"paymentInfo_timeout,id: "+id+" 耗时(秒):" + time;
    }
    /**
     * 降级方法
     */
    public String paymentInfo_timeout_handler(Integer id){
        return "线程池:  "+Thread.currentThread().getName()+"paymentInfo_timeout_handler,id: "+id+" o(╥﹏╥)o";
    }

    //===============服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreakerFallBack",commandProperties = {
           @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),// 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),//请求次数   10秒内请求10次  失败率达到60%
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//时间窗口期--时间范围
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60")//失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        if (id < 0){
            throw new RuntimeException("*****id 不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "\t" + "调用成功，流水号："+serialNumber;
    }

    public String paymentCircuitBreakerFallBack(@PathVariable("id") Integer id){
        return "id 不能为负数，请稍后再试，😭 ID：" + id;
    }
}
