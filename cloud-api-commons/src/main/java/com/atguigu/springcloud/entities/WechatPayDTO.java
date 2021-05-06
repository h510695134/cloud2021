package com.atguigu.springcloud.entities;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author wanghh
 * @date 2021-04-30
 */
@Data
@Builder
public class WechatPayDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 直连商户号
     */
    private String mchid;

    /**
     * 商户订单号
     */
    private String out_trade_no;

    /**
     * 应用ID
     */
    private String appid;

    /**
     * 商品描述
     */
    private String description;

    /**
     * 通知地址
     */
    private String notify_url;

    /**
     * 订单金额信息
     */
    private WechatPayAmountDTO amount;

}
