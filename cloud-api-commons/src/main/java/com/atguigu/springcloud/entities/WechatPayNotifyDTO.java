package com.atguigu.springcloud.entities;

import lombok.Data;

import java.io.Serializable;

/**
 * 微信支付异步通知传参
 *
 * @author wanghh
 * @date 2021/04/26/17:56
 * @description
 */
@Data
public class WechatPayNotifyDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 通知ID
     */
    private String id;
    /**
     * 通知创建时间
     */
    private String create_time;
    /**
     * 通知类型
     */
    private String event_type;
    /**
     * 通知数据类型
     */
    private String resource_type;
    /**
     * 通知数据
     */
    private WechatPayResourceDTO resource;
    /**
     * 回调摘要
     */
    private String summary;

}
