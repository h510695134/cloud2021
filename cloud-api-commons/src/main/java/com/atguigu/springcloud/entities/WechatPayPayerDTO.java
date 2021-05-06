package com.atguigu.springcloud.entities;

import lombok.Data;

import java.io.Serializable;

/**
 * 微信支付异步通知传参-支付者信息
 *
 * @author wanghh
 * @date 2021/04/27/08:48
 * @description
 */
@Data
public class WechatPayPayerDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 用户标识
     */
    private String openid;
}
