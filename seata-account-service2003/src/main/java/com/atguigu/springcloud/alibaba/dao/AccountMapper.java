package com.atguigu.springcloud.alibaba.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * @author wanghh
 * @date 2021-04-8
 */
@Mapper
public interface AccountMapper {

    /**
     * 账户扣减
     * @param userId userId
     * @param money money
     * @return 结果
     */
    int decrease(@Param("userId") Long userId, @Param("money") BigDecimal money);
}
