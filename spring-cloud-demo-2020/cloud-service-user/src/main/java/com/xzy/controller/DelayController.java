package com.xzy.controller;

import com.xzy.msg.MessageBox;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 模拟下行服务延时
 *
 * @author xzy
 * @date 2022/1/12 15:08
 */
@RestController
@RequestMapping("/user/delay")
@Slf4j
public class DelayController {
    @Value("${server.port}")
    private String serverPort;

    /**
     * 处理时间很慢的RPO
     */
    @GetMapping("/delay")
    public MessageBox<String> delayRpo() {
        // 模拟：出现异常
        if (Math.random() > 0.5) {
            throw new IllegalArgumentException("随机异常");
        }

        // 模拟：处理缓慢
        int delayMilliseconds = 5000;
        try {
            TimeUnit.MILLISECONDS.sleep(delayMilliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String msg = "Server port：" + serverPort + " UUID：" + UUID.randomUUID().toString() + " Current thread：" + Thread.currentThread();
        return MessageBox.ok(msg);
    }
}
