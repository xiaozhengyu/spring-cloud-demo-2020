package com.xzy.controller;

import com.xzy.msg.MessageBox;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 服务监控
 *
 * @author xzy
 * @date 2022/1/6 10:55
 */
@RestController
@RequestMapping(path = "/health")
public class HealthController {
    @Value("${server.port}")
    private String serverPort;

    @GetMapping
    MessageBox<String> getServerInfo() {
        return MessageBox.ok("from " + serverPort);
    }
}
