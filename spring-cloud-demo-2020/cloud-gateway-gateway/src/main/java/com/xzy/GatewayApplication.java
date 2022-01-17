package com.xzy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 说明：服务网关
 *
 * @author xzy
 * @date 2022/1/16  22:22
 */
@SpringBootApplication
@EnableEurekaClient
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

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
}
