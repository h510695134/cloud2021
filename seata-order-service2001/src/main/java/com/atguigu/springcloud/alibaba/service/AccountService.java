package com.atguigu.springcloud.alibaba.service;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author wanghh
 * @date 2021-04-7
 */
@FeignClient(value = "seata-account-service")
public interface AccountService {
}
