package com.atguigu.springcloud.entities;

import lombok.Data;

import java.io.Serializable;

/**
 * 微信支付异步通知传参-订单金额信息
 *
 * @author wanghh
 * @date 2021/04/27/08:51
 * @description
 */
@Data
public class WechatPayNotifyAmountDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 支付金额，单位为分
     */
    private Integer total;
    /**
     * 用户支付金额，单位为分
     */
    private Integer payer_total;
    /**
     * CNY：人民币，境内商户号仅支持人民币。
     */
    private String currency;
    /**
     * 用户支付币种
     */
    private String payer_currency;

}
