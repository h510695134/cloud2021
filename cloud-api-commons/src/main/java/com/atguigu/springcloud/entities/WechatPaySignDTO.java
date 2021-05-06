package com.atguigu.springcloud.entities;

import lombok.Data;

import java.io.Serializable;

/**
 * 微信支付生成签名传参
 *
 * @author wanghh
 * @date 2021/04/26/11:23
 * @description
 */
@Data
public class WechatPaySignDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 应用ID
     */
    private String appId;
    /**
     * 时间戳
     */
    private Long timestamp;
    /**
     * 随机字符串
     */
    private String nonceStr;
    /**
     * 预支付交易会话ID
     */
    private String prepayId;

    public WechatPaySignDTO() {
    }

    public WechatPaySignDTO(String appId, Long timestamp, String nonceStr, String prepayId) {
        this.appId = appId;
        this.timestamp = timestamp;
        this.nonceStr = nonceStr;
        this.prepayId = prepayId;
    }
}
