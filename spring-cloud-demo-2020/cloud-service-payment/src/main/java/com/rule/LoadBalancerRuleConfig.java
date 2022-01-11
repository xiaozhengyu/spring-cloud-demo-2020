package com.rule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 设置：负载均衡策略
 *
 * @author xzy
 * @date 2022/1/11 9:49
 */
@Configuration
public class LoadBalancerRuleConfig {
    /**
     * Ribbon自带的负载均衡实现类（部分）：
     * -- RoundRobinRule：在现有服务实例之间轮询分配流量
     * -- WeightedResponseTimeRule：拓展自RoundRobinRule，分配流量时偏向平均响应时间短的服务实例
     * -- RandomRule：在现有服务实例之间随机分配流量
     * -- RetryRule：拓展自RoundRobinRule，如果获取服务失败则在指定时间内进行重试，获取可用服务实例
     * -- BestAvailableRule：先过滤掉由于多次访问故障而处于断路器跳闸状态的实例，然后选择一个并发量最小的实例
     * -- AvailabilityFilteringRule：先过滤掉故障实例，再选择并发较小的实例
     * -- ZoneAvoidanceRule：（默认规则）综合判断实例的性能和可用性。
     */
    @Bean
    public IRule iRule() {
        return new RandomRule();
    }
}
