package com.atguigu.springcloud.entities;

import com.alipay.easysdk.kernel.Config;
import lombok.Data;

import java.io.Serializable;

/**
 * 支付宝退款传参
 * @author wanghh
 * @date 2021-04-29
 */
@Data
public class AliPayRefundDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 支付订单编码
     */
    private String outTradeNo;
    /**
     * 支付宝配置
     */
    private Config config;
    /**
     * 退款金额
     */
    private String refundAmount;
    /**
     * 部分退款单号
     */
    private String outRequestNo;

    public AliPayRefundDTO(String outTradeNo, Config config, String refundAmount, String outRequestNo) {
        this.outTradeNo = outTradeNo;
        this.config = config;
        this.refundAmount = refundAmount;
        this.outRequestNo = outRequestNo;
    }
    public AliPayRefundDTO(){

    }
}
