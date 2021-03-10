package com.atguigu.springcloud.service;

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

    public String paymentInfo_timeout(Integer id){
        int time  = 3;
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池:  "+Thread.currentThread().getName()+"paymentInfo_timeout,id: "+id+" 耗时(秒):" + time;
    }
}
