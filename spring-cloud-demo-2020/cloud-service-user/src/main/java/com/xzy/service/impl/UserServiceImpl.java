package com.xzy.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.xzy.entities.UserEntity;
import com.xzy.msg.MessageBox;
import com.xzy.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 用户管理
 *
 * @author xzy
 * @date 2022/1/10 10:11
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Value("${server.port}")
    private String serverPort;

    private final Map<Long, UserEntity> TEST_RECORDS = new HashMap<>(3);

    {
        TEST_RECORDS.put(1001L, new UserEntity(1001L, "张三", "男", 21));
        TEST_RECORDS.put(1002L, new UserEntity(1002L, "李四", "女", 22));
        TEST_RECORDS.put(1003L, new UserEntity(1003L, "王五", "男", 23));
    }

    /**
     * 根据主键查询用户信息
     *
     * @param id 主键
     * @return 用户信息
     */
    @Override
    public MessageBox<UserEntity> findByPrimaryKey(Long id) {
        if (!TEST_RECORDS.containsKey(id)) {
            throw new IllegalArgumentException("无效的ID");
        }
        String msg = "Server port：" + serverPort + " UUID：" + UUID.randomUUID().toString() + " Current thread：" + Thread.currentThread();
        return MessageBox.ok(msg, TEST_RECORDS.get(id));
    }

    /**
     * 根据主键查询用户信息（带有服务熔断机制）
     *
     * @param id 主键
     * @return 用户信息
     */
    @Override
    @HystrixCommand(
            fallbackMethod = "findByPrimaryKeyCircuitBreaker",
            commandProperties = {
                    // 如果1000ms内请求次数超过10次，且失败率超过60%，启动熔断机制，熔断10000ms
                    // 熔断期间直接调用熔断方法处理请求，熔断结束后试探性处理请求，如果成功则恢复服务，否则继续熔断
                    @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "1000"), // 统计窗口
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"), // 最小请求数
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"), // 错误请求比例
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000") // 修复窗口（）
            }
    )
    public MessageBox<UserEntity> findByPrimaryKeyWithCircuitBreaker(Long id) {
        log.info("正常调用...");
        return findByPrimaryKey(id);
    }

    public MessageBox<UserEntity> findByPrimaryKeyCircuitBreaker(Long id) {
        log.error("触发熔断...");
        String msg = "Server port：" + serverPort + " UUID：" + UUID.randomUUID().toString() + " Current thread：" + Thread.currentThread() + " （触发熔断，请稍后重试！！!）";
        return MessageBox.ok(msg, null);
    }
}
