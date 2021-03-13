package com.atguigu.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

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

}
