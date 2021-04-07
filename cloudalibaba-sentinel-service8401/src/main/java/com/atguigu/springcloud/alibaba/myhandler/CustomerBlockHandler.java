package com.atguigu.springcloud.alibaba.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entities.CommonResult;

/**
 * @author wanghh
 * @date 2021-04-06
 */
public class CustomerBlockHandler {

    public static CommonResult handlerException(BlockException exception){
        return new CommonResult(444,"按客户自定义,global handlerException------1");
    }

    public static CommonResult handlerException2(BlockException exception){
        return new CommonResult(444,"按客户自定义,global handlerException------2");
    }
}
