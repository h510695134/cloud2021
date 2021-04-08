package com.atguigu.springcloud.alibaba.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author wanghh
 * @date 2021-04-8
 */
@Mapper
public interface StorageMapper {


    /**
     * 库存扣减
     * @param productId productId
     * @param count count
     * @return 结果
     */
    int decrease(@Param("productId") Long productId, @Param("count") Integer count);
}
