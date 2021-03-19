package com.atguigu.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 代码配置路由
 * @program: cloud2021
 * @description
 * @author: wanghh
 * @create: 2021-03-18 20:50
 **/
@Configuration
public class GateWayConfig {

    @Bean
    public RouteLocator customerRouteLocator(RouteLocatorBuilder routeLocatorBuilder){
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        routes.route("path_route_haha", // 路由id 唯一
                r -> r.path("/guonei") // gateway暴露地址
                        .uri("http://news.baidu.com/guonei")) // 实际访问地址
                .build();
        return routes.build();
    }
}
