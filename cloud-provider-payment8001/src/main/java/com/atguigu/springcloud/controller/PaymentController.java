package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: cloud2021
 * @description
 * @author: wanghh
 * @create: 2021-03-01 20:24
 **/
@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping("/payment/create")
    public CommonResult create(Payment payment){
        int result = paymentService.create(payment);
        if (result > 0){
            return new CommonResult(200,"插入成功,serverPort="+serverPort,result);
        }else {
            return new CommonResult(400,"插入失败",result);
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult getPayment(@PathVariable("id") Long id){
        Payment result = paymentService.getPayment(id);
        if (result != null){
            return new CommonResult(200,"查询成功,serverPort="+serverPort,result);
        }else {
            return new CommonResult(400,"查询失败",null);
        }
    }

    /**
     * 获取注册进eureka中的服务  相关信息
     * @return
     */
    @GetMapping("/payment/discovery")
    public Object discovery(){
        // 获取微服务名称
        List<String> services = discoveryClient.getServices();
        services.forEach(e -> System.out.println("****" + e));

        // 获取微服务的具体信息
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-ORDER-SERVICE");
        instances.forEach(e -> {
           log.info(e.getServiceId()+"\t"+e.getHost()+"\t"+e.getPort());
        });
        return this.discoveryClient;
    }

    /**
     * 手写负载均衡算法-1
     * @return
     */
    @GetMapping("/payment/lb")
    public String getPaymentLB(){
        return serverPort;
    }
}
