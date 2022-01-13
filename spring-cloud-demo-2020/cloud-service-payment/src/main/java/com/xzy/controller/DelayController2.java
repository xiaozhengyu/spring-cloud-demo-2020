package com.xzy.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.xzy.feign.UserServiceFeign;
import com.xzy.msg.MessageBox;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * 调用处理非常缓慢的RPO
 *
 * @author xzy
 * @date 2022/1/4 15:51
 */
@RestController
@RequestMapping(path = "/payment/delay2")
@Slf4j
@DefaultProperties(defaultFallback = "planDefault") // 默认的服务降级
public class DelayController2 {
    @Value("${server.port}")
    private String serverPort;

    private final UserServiceFeign userServiceFeign;

    @Autowired
    public DelayController2(UserServiceFeign userServiceFeign) {
        this.userServiceFeign = userServiceFeign;
    }

    /**
     * 下行服务直接躺平 + 上行服务直接躺平
     */
    @GetMapping("/delay_with_no_fallback")
    @HystrixCommand
    public MessageBox<String> delayRpo() {
        return userServiceFeign.delayRpo();
    }

    /**
     * 下行服务直接躺平 + 上行服务设置服务降级机制
     */
    @GetMapping("/delay_with_uplink_fallback")
    @HystrixCommand(fallbackMethod = "planB", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000") // 超时时间
    })
    public MessageBox<String> delayRpoWithUplinkServiceFallback() {
        return userServiceFeign.delayRpo();
    }

    /**
     * 下行服务设置服务降级机制 + 上行服务躺平
     */
    @GetMapping("/delay_with_downlink_fallback")
    @HystrixCommand
    public MessageBox<String> delayRpoWithDownlinkServiceFallback() {
        return userServiceFeign.delayRpoWithFallback();
    }

    /**
     * 下行服务设置服务降级机制 + 上行服务设置服务降级机制
     */
    @GetMapping("/delay_with_updown_fallback")
    @HystrixCommand(fallbackMethod = "planB", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000") // 超时时间
    })
    public MessageBox<String> delayRpoWithUplinkAndDownlinkServiceFallback() {
        return userServiceFeign.delayRpoWithFallback();
    }

    /*----------fallback method----------*/

    public MessageBox<String> planB() {
        log.info("处理失败，出现异常或超时，进行服务降级...");
        String msg = "Server port：" + serverPort + " UUID：" + UUID.randomUUID().toString() + " Current thread：" + Thread.currentThread() + " (Plan B：上行服务)";
        return MessageBox.ok(msg);
    }

    public MessageBox<String> planDefault() {
        log.info("处理失败，出现异常或超时，进行服务降级...");
        String msg = "Server port：" + serverPort + " UUID：" + UUID.randomUUID().toString() + " Current thread：" + Thread.currentThread() + " (Plan B：上行服务)";
        return MessageBox.ok(msg);
    }
}
