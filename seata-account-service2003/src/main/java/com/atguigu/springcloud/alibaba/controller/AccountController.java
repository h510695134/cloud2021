package com.atguigu.springcloud.alibaba.controller;

import com.atguigu.springcloud.alibaba.service.AccountService;
import com.atguigu.springcloud.entities.CommonResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author wanghh
 * @date 2021-04-8
 */
@RestController
public class AccountController {

    @Resource
    private AccountService accountService;

    /**
     * 账户扣减
     * @param userId userId
     * @param money money
     * @return 结果
     */
    @PostMapping("/account/decrease")
    CommonResult decrease(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money){
        int decrease = accountService.decrease(userId, money);
        if (decrease > 0){
            return new CommonResult(200,"账户扣减成功");
        }else {
            return new CommonResult(400,"账户扣减失败");
        }
    }
}
