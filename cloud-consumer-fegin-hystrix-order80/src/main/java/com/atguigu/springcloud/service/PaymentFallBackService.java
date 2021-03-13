package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * 服务降级,异常处理
 * @program: cloud2021
 * @description
 * @author: wanghh
 * @create: 2021-03-13 21:27
 **/
@Component
public class PaymentFallBackService implements PaymentHystrixService{
    @Override
    public String paymentInfo_ok(Integer id) {
        return "----------fallback,PaymentHystrixService,paymentInfo_ok异常处理";
    }

    @Override
    public String paymentInfo_timeout(Integer id) {
        return "---------fallback,PaymentHystrixService,paymentInfo_timeout异常处理";
    }
}
