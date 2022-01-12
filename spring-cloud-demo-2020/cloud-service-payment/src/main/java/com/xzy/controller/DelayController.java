package com.xzy.controller;

import com.xzy.feign.DelayControllerFeign;
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
    private final DelayControllerFeign delayControllerFeign;

    @Autowired
    public DelayController(DelayControllerFeign delayControllerFeign) {
        this.delayControllerFeign = delayControllerFeign;
    }

    /**
     * 躺平：如果RPO调用失败，直接将失败信息返回，如果RPO响应缓慢，直接慢慢等待。
     */
    @GetMapping("/delay")
    public MessageBox<String> delayRpo() {
        return delayControllerFeign.delayRpo();
    }

    /**
     * 客户端服务降级
     */
    @GetMapping("/delay")
    public MessageBox<String> delayRpoWithClientFallback() {
        return delayControllerFeign.delayRpo();
    }
}
