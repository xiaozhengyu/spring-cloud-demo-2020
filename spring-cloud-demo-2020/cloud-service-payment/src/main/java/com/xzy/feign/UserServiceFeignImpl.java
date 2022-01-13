package com.xzy.feign;

import com.xzy.entities.UserEntity;
import com.xzy.msg.MessageBox;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * 说明：
 *
 * @author xzy
 * @date 2022/1/13  16:31
 */
@Service
@Slf4j
public class UserServiceFeignImpl implements UserServiceFeign {

    @Value("${server.port}")
    private String serverPort;

    /**
     * 根据主键查询用户信息
     *
     * @param id 主键
     * @return 用户信息
     */
    @Override
    public MessageBox<UserEntity> findByPrimaryKey(Long id) {
        log.info("处理失败，出现异常或超时，进行服务降级...");
        String msg = "Server port：" + serverPort + " UUID：" + UUID.randomUUID().toString() + " Current thread：" + Thread.currentThread() + " (Plan B：上行服务)";
        return MessageBox.ok(msg, null);
    }

    /**
     * 处理时间很慢的RPO
     */
    @Override
    public MessageBox<String> delayRpo() {
        log.info("处理失败，出现异常或超时，进行服务降级...");
        String msg = "Server port：" + serverPort + " UUID：" + UUID.randomUUID().toString() + " Current thread：" + Thread.currentThread() + " (Plan B：上行服务)";
        return MessageBox.ok(msg);
    }

    /**
     * 服务降级：处理超时或出现异常，执行 ”Plan B“
     */
    @Override
    public MessageBox<String> delayRpoWithFallback() {
        log.info("处理失败，出现异常或超时，进行服务降级...");
        String msg = "Server port：" + serverPort + " UUID：" + UUID.randomUUID().toString() + " Current thread：" + Thread.currentThread() + " (Plan B：上行服务)";
        return MessageBox.ok(msg);
    }
}
