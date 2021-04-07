package com.atguigu.springcloud.alibaba.service;

import com.atguigu.springcloud.alibaba.domain.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author wanghh
 * @date 2021-04-7
 */
@FeignClient(value = "seata-storage-service")
public interface StorageService {

    /**
     * 库存扣减
     * @param productId productId
     * @param count count
     * @return 结果
     */
    @PostMapping(value = "/storage/decrease")
    CommonResult decrease(@RequestParam("productId") Long productId,@RequestParam("count") Integer count);
}
