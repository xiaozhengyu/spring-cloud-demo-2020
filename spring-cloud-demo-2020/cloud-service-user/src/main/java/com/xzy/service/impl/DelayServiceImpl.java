package com.xzy.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.xzy.msg.MessageBox;
import com.xzy.service.DelayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author xzy
 * @date 2022/1/12 17:21
 */
@Service
@Slf4j
public class DelayServiceImpl implements DelayService {
    @Value("${server.port}")
    private String serverPort;

    /**
     * 直接躺平：慢慢处理 + 抛出异常
     */
    @Override
    public MessageBox<String> delayRpo() {
        // 模拟：出现异常
        if (Math.random() > 0.7) { // 30%概率的出现异常
            log.info("抛出异常");
            throw new IllegalArgumentException("随机异常");
        }

        // 模拟：处理缓慢
        int delayMilliseconds = (int) (Math.random() * 10000); // 随机处理时间 0~10000ms
        try {
            log.info("执行时间：{}ms", delayMilliseconds);
            TimeUnit.MILLISECONDS.sleep(delayMilliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info("正常处理...");
        String msg = "Server port：" + serverPort + " UUID：" + UUID.randomUUID().toString() + " Current thread：" + Thread.currentThread();
        return MessageBox.ok(msg);
    }

    /**
     * 服务降级：如果处理超时或出现异常，执行 ”Plan B“
     */
    @Override
    @HystrixCommand(fallbackMethod = "planB", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000") // 超时时间
    })
    public MessageBox<String> delayRpoWithFallback() {
        return delayRpo();
    }

    /*----------fallback method----------*/

    public MessageBox<String> planB() {
        log.error("处理失败，出现异常或超时，进行服务降级...");
        String msg = "Server port：" + serverPort + " UUID：" + UUID.randomUUID().toString() + " Current thread：" + Thread.currentThread() + " (Plan B)";
        return MessageBox.ok(msg);
    }
}
