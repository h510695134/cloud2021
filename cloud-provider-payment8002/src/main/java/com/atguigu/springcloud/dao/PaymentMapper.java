package com.atguigu.springcloud.dao;

import com.atguigu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author wanghh
 * @date 2021-02-22
 */
@Mapper
public interface PaymentMapper {

    public int create(Payment payment);

    public Payment getPayment(@Param("id") Long id);
}
