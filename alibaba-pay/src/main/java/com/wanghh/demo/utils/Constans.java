package com.wanghh.demo.utils;

/**
 * @author wanghh
 * @date 2021-05-6
 */
public class Constans {
    /**
     * 微信预支付交易会话标识
     */
    public final String WechatPayPrePayId = "prepay_id";
    /**
     * 微信预支付交易会话标识
     */
    public final String CURRENCY = "CNY";
    /**
     * 微信支付订单详情扩展字符串
     */
    public final String PACKAGE = "Sign=WXPay";
    /**
     * 微信支付成功状态类型
     */
    public static final String WechatPayEventType="TRANSACTION.SUCCESS";
    /**
     * 支付宝退款成功编码
     */
    public final String WechatPayRefundStatus = "SUCCESS";
}
