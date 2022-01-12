package com.xzy.controller;

import com.xzy.msg.MessageBox;
import com.xzy.service.DelayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    private final DelayService delayService;

    @Autowired
    public DelayController(DelayService delayService) {
        this.delayService = delayService;
    }

    /**
     * 直接躺平：慢慢处理 + 抛出异常
     */
    @GetMapping("/delay")
    public MessageBox<String> delayRpo() {
        return delayService.delayRpo();
    }

    /**
     * 服务降级：如果处理超时或出现异常，执行 ”Plan B“
     */
    @GetMapping("/delay_with_fallback")
    public MessageBox<String> delayRpoWithFallback() {
        return delayService.delayRpoWithFallback();
    }
}
