package com.atguigu.springcloud.alibaba.dao;

import com.atguigu.springcloud.alibaba.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author wanghh
 * @date 2021-04-7
 */
@Mapper
public interface OrderMapper {

    /**
     * 创建订单
     * @param order order
     */
    void create(Order order);

    /**
     * 修改订单状态
     * @param userId userId
     * @param status status
     */
    void update(@Param("userId") Long userId,@Param("status") Integer status);
}
