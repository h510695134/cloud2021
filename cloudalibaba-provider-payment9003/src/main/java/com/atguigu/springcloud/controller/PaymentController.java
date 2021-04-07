package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author wanghh
 * @date 2021-04-6
 */
@RestController
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    public static HashMap<Long, Payment> hashMap = new HashMap<>(16);

    static {
        hashMap.put(1L,new Payment(1L,"serial00001"));
        hashMap.put(2L,new Payment(2L,"serial00002"));
        hashMap.put(3L,new Payment(3L,"serial00003"));
    }

    @GetMapping("/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id){
        Payment payment = hashMap.get(id);
        return new CommonResult<>(200, "from mysql,serverport:" + serverPort, payment);
    }
}
