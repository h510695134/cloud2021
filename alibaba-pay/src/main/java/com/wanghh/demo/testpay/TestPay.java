package com.wanghh.demo.testpay;

import cn.hutool.core.util.ObjectUtil;
import com.atguigu.springcloud.entities.AliPayVerifyDTO;
import com.wanghh.demo.utils.AlipayUtils;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

/**
 * @author wanghh
 * @date 2021-04-30
 */
public class TestPay {

    @Resource
    private AlipayUtils alipayUtils;

    /**
     * B端支付宝异步通知--> 支付宝回调地址
     */
    @PostMapping("/app/aliPayApp/receiveSignNotify")
    public void getAliPayCallback2B(HttpServletResponse response, HttpServletRequest request) throws Exception {
        //todo 获取订单相关信息
        /*Order order = commonService.findOrderByForderCode(request.getParameter("out_trade_no"));
        if (ObjectUtil.isEmpty(order)) {
            order = new Order();
            order.setForderPayPrice(BigDecimal.ZERO);
        }*/
        //返回支付成功
        if (alipayUtils.aliPayResultCheck(response, request,
                AliPayVerifyDTO.builder()
                        .config(AlipayUtils.App2B.CONFIG)
                        .sellerId("2088041400518764")
                        .totalAmount("1000").build()).equals("TRADE_SUCCESS")) {
            //todo 更改订单相关信息
            //orderBaseService.changeOrderPayStatus(request.getParameter("out_trade_no"));
        }
    }
}
