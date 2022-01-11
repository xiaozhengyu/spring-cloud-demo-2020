package com.xzy;

import com.rule.LoadBalancerRuleConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;

/**
 * 支付服务
 *
 * @author xzy
 * @date 2022/1/4 15:35
 */
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@RibbonClients({
        // 为每个服务调用单独设置负载均衡规则
        @RibbonClient(name = "cloud-user-service", configuration = LoadBalancerRuleConfig.class)
})
public class PaymentApplication {
    public static void main(String[] args) {
        SpringApplication.run(PaymentApplication.class, args);
    }
}
