package com.atguigu.springcloud.entities;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 支付宝传入参数
 * @author wanghh
 * @date 2021-04-29
 */
@Data
@Builder
public class AliPayDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 订单编码
     */
    private String outTradeNo;

    /**
     * 支付金额
     */
    private String totalAmount;
    /**
     * 订单主题
     */
    private String subject;
}
