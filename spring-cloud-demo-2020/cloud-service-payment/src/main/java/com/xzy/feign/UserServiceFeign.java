package com.xzy.feign;

import com.xzy.entities.UserEntity;
import com.xzy.msg.MessageBox;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author xzy
 * @date 2022/1/11 14:10
 */
@Service
@FeignClient("cloud-user-service")
public interface UserServiceFeign {

    /**
     * 根据主键查询用户信息
     *
     * @param id 主键
     * @return 用户信息
     */
    @GetMapping("/user/user/{id}")
    MessageBox<UserEntity> findByPrimaryKey(@PathVariable("id") Long id);

    /**
     * 根据主键查询用户信息（带有服务熔断机制）
     *
     * @param id 主键
     * @return 用户信息
     */
    @GetMapping("/user/user/with_circuit_breaker/{id}")
    MessageBox<UserEntity> findByPrimaryKeyWithCircuitBreaker(@PathVariable("id") Long id);

    /**
     * 处理时间很慢的RPO
     */
    @GetMapping("/user/delay/delay")
    MessageBox<String> delayRpo();

    /**
     * 服务降级：处理超时或出现异常，执行 ”Plan B“
     */
    @GetMapping("/user/delay/delay_with_fallback")
    MessageBox<String> delayRpoWithFallback();
}
