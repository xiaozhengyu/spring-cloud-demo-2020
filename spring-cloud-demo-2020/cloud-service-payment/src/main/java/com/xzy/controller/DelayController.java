package com.xzy.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.xzy.feign.UserServiceFeign;
import com.xzy.msg.MessageBox;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 调用处理非常缓慢的RPO
 *
 * @author xzy
 * @date 2022/1/4 15:51
 */
@RestController
@RequestMapping(path = "/payment/delay")
@Slf4j
public class DelayController {

    private final UserServiceFeign userServiceFeign;

    @Autowired
    public DelayController(UserServiceFeign userServiceFeign) {
        this.userServiceFeign = userServiceFeign;
    }

    /**
     * 下行服务直接躺平 + 上行服务直接躺平
     */
    @GetMapping("/delay_with_no_fallback")
    public MessageBox<String> delayRpo() {
        return userServiceFeign.delayRpo();
    }

    /**
     * 下行服务直接躺平 + 上行服务设置服务降级机制
     */
    @GetMapping("/delay_with_uplink_fallback")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000") // 超时时间
    })
    public MessageBox<String> delayRpoWithUplinkServiceFallback() {
        return userServiceFeign.delayRpo();
    }

    /**
     * 下行服务设置服务降级机制 + 上行服务躺平
     */
    @GetMapping("/delay_with_downlink_fallback")
    public MessageBox<String> delayRpoWithDownlinkServiceFallback() {
        return userServiceFeign.delayRpoWithFallback();
    }

    /**
     * 下行服务设置服务降级机制 + 上行服务设置服务降级机制
     */
    @GetMapping("/delay_with_updown_fallback")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000") // 超时时间
    })
    public MessageBox<String> delayRpoWithUplinkAndDownlinkServiceFallback() {
        return userServiceFeign.delayRpoWithFallback();
    }
}
