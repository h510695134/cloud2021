package com.atguigu.springcloud.alibaba.service.impl;

import com.atguigu.springcloud.alibaba.dao.AccountMapper;
import com.atguigu.springcloud.alibaba.service.AccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author wanghh
 * @date 2021-04-8
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountMapper accountMapper;
    /**
     * 账户扣减
     * @param userId userId
     * @param money money
     * @return 结果
     */
    @Override
    public int decrease(Long userId, BigDecimal money) {
        return accountMapper.decrease(userId,money);
    }
}
