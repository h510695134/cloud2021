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
public class WechatPayAmountDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 订单总金额，单位为分。
     */
    private Integer total;

    /**
     * CNY：人民币，境内商户号仅支持人民币。
     */
    private String currency;
}
