package com.xzy.controller;

import com.xzy.feign.UserServiceFeign;
import com.xzy.msg.MessageBox;
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
public class DelayController {
    private final UserServiceFeign userServiceFeign;

    @Autowired
    public DelayController(UserServiceFeign userServiceFeign) {
        this.userServiceFeign = userServiceFeign;
    }

    /**
     * 下行服务直接躺平：处理缓慢 + 可能抛出异常
     */
    @GetMapping("/delay")
    public MessageBox<String> delayRpo() {
        return userServiceFeign.delayRpo();
    }

    /**
     * 下行服务有服务降级机制：在有限时间内返回有效结果
     */
    @GetMapping("/delay_with_fallback")
    public MessageBox<String> delayRpoWithClientFallback() {
        return userServiceFeign.delayRpoWithFallback();
    }
}
