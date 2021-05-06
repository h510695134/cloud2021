package com.atguigu.springcloud.entities;

import com.alipay.easysdk.kernel.Config;
import lombok.Builder;
import lombok.Data;

/**
 * 支付宝回调验签
 * @author wanghh
 * @date 2021-04-29
 */
@Data
@Builder
public class AliPayVerifyDTO {

    private static final long serialVersionUID = 1L;
    /**
     * 支付宝配置
     */
    private Config config;
    /**
     * 支付金额
     */
    private String totalAmount;
    /**
     * 卖家支付宝用户号UID
     */
    private String sellerId;
}
