package com.xzy.config;

import com.xzy.filter.LogGatewayFilter;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;

/**
 * 网关配置
 *
 * @author xzy
 * @date 2022/1/17 10:55
 */
@SpringBootConfiguration
public class GatewayConfig {
    /**
     * 设置路由规则的方式：
     * 1.通过application文件
     * 2.通过RouteLocator
     */
//    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        RouteLocatorBuilder.Builder routes = builder.routes();
        routes.route("payment_route", r -> r.path("/payment/**").uri("http://localhost:9001"));
        routes.route("user_route", r -> r.path("/user/**").uri("http://localhost:8001"));
        return routes.build();
    }

    /**
     * 自定义过滤器
     */
//    @Bean
    public LogGatewayFilter logGatewayFilter() {
        return new LogGatewayFilter();
    }
}
