package com.atguigu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @author wanghh
 * @date 2021-03-08
 */
public interface LoadBalancer {



    ServiceInstance instances(List<ServiceInstance> serviceInstances);
}
