package com.atguigu.springcloud.alibaba.service;


import java.math.BigDecimal;

/**
 * @author wanghh
 * @date 2021-04-
 */
public interface AccountService {

    /**
     * 账户扣减
     * @param userId userId
     * @param money money
     * @return 结果
     */
    int decrease(Long userId,BigDecimal money);
}
