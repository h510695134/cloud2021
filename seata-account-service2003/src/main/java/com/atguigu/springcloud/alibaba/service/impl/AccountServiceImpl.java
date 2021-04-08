package com.atguigu.springcloud.alibaba.service.impl;

import com.atguigu.springcloud.alibaba.dao.AccountMapper;
import com.atguigu.springcloud.alibaba.service.AccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

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
        // 模拟超时异常，全局事务回滚
        // openfeign 默认等待1秒钟 还有超时重试机制 暂停几秒钟
        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return accountMapper.decrease(userId,money);
    }
}
