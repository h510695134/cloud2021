package com.atguigu.springcloud.mylog;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;

/**
 * 自定义gateway 全局过滤链filter
 * @program: cloud2021
 * @description
 * @author: wanghh
 * @create: 2021-03-20 21:32
 **/
@Component
@Slf4j
public class MyLogGatewayFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("**************come in MyLogGatewayFilter: " + new Date());
        String uname = exchange.getRequest().getQueryParams().getFirst("uname");
        if (StringUtils.isEmpty(uname)){
            log.error("**************用户名为null,非法用户,o(╥﹏╥)o");
            //设置状态码
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            // 返回
            return exchange.getResponse().setComplete();
        }
        //校验通过 放行
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
