package com.atguigu.springcloud.entities;

import lombok.Data;

import java.io.Serializable;

/**
 * 微信支付异步通知传参-通知数据
 *
 * @author wanghh
 * @date 2021/04/27/11:46
 * @description
 */
@Data
public class WechatPayResourceDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 加密算法类型
     */
    private String algorithm;
    /**
     * 数据密文
     */
    private String ciphertext;
    /**
     * 附加数据
     */
    private String associated_data;
    /**
     * 原始类型
     */
    private String original_type;
    /**
     * 随机串
     */
    private String nonce;
}
