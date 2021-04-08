package com.atguigu.springcloud.alibaba.controller;

import com.atguigu.springcloud.alibaba.service.StorageService;
import com.atguigu.springcloud.entities.CommonResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wanghh
 * @date 2021-04-8
 */
@RestController
public class StorageController {

    @Resource
    private StorageService storageService;

    /**
     * 扣减库存
     * @param productId productId
     * @param count count
     * @return 结果
     */
    @PostMapping(value = "/storage/decrease")
    CommonResult decrease(@RequestParam("productId") Long productId, @RequestParam("count") Integer count){
        int decrease = storageService.decrease(productId, count);
        if (decrease > 0){
            return new CommonResult(200,"库存扣减成功");
        }else {
            return new CommonResult(400,"库存扣减失败");
        }
    }
}
