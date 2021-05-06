package com.atguigu.springcloud.entities;

import lombok.Data;

import java.io.Serializable;

/**
 * 微信支付异步通知传参-解密后的通知数据
 *
 * @author wanghh
 * @date 2021/04/26/17:59
 * @description
 */
@Data
public class WechatPayNotifyDataDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 应用ID
     */
    private String appid;
    /**
     * 商户号
     */
    private String mchid;
    /**
     * 商户订单号
     */
    private String out_trade_no;
    /**
     * 微信支付订单号
     */
    private String transaction_id;
    /**
     * 交易类型
     */
    private String trade_type;
    /**
     * 交易状态
     */
    private String trade_state;
    /**
     * 交易状态描述
     */
    private String trade_state_desc;
    /**
     * 付款银行
     */
    private String bank_type;
    /**
     * 附加数据
     */
    private String attach;
    /**
     * 支付完成时间
     */
    private String success_time;
    /**
     * 支付者信息
     */
    private WechatPayPayerDTO payer;
    /**
     * 订单金额信息
     */
    private WechatPayNotifyAmountDTO amount;

}
