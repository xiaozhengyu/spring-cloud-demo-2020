package com.xzy.controller;

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
    public MessageBox<String> delayRpoWithUplinkAndDownlinkServiceFallback() {
        return userServiceFeign.delayRpoWithFallback();
    }
}
