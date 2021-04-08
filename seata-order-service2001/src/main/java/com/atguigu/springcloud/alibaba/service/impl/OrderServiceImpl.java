package com.atguigu.springcloud.alibaba.service.impl;

import com.atguigu.springcloud.alibaba.dao.OrderMapper;
import com.atguigu.springcloud.alibaba.domain.Order;
import com.atguigu.springcloud.alibaba.service.AccountService;
import com.atguigu.springcloud.alibaba.service.OrderService;
import com.atguigu.springcloud.alibaba.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author wanghh
 * @date 2021-04-7
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private StorageService storageService;

    @Resource
    private AccountService accountService;

    @Override
    @GlobalTransactional
    public void create(Order order) {
        log.info("-----开始创建订单");
        // 新建订单
        orderMapper.create(order);
        log.info("------订单微服务开始调用库存,做扣减");
        // 库存扣减
        storageService.decrease(order.getProductId(),order.getCount());
        log.info("------订单微服务开始调用库存,做扣减end");

        log.info("------订单微服务开始调用账户,做扣减");
        // 账户扣减
        accountService.decrease(order.getUserId(),order.getMoney());
        log.info("------订单微服务开始调用账户,做扣减end");

        // 修改订单状态 从进行中到已完成
        log.info("------修改订单状态开始");
        orderMapper.update(order.getUserId(),0);
        log.info("------修改订单状态结束");

        log.info("-----下单结束了，哈哈哈");
    }

}
