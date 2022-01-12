package com.xzy.controller;

import com.xzy.feign.DelayControllerFeign;
import com.xzy.msg.MessageBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
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
     * 处理时间很慢的RPO
     */
    @GetMapping("/delay")
    public MessageBox<String> delayRpo() {
        return delayControllerFeign.delayRpo();
    }

    /**
     * 处理时间很慢的RPO
     */
    @GetMapping("/delay")
    public MessageBox<String> delayRpoWithClientFallback() {
        return delayControllerFeign.delayRpo();
    }
}
