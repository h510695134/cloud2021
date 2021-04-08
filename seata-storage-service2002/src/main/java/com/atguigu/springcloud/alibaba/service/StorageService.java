package com.atguigu.springcloud.alibaba.service;

import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author wanghh
 * @date 2021-04-
 */
public interface StorageService {

    /**
     * 库存扣减
     * @param productId productId
     * @param count count
     * @return 结果
     */
    int decrease(Long productId,Integer count);
}
