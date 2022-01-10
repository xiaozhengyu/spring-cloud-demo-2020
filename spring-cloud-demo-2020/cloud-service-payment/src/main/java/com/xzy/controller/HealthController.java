package com.xzy.controller;

import com.xzy.msg.MessageBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 服务监控
 *
 * @author xzy
 * @date 2022/1/6 10:55
 */
@RestController
@RequestMapping(path = "/payment/health")
public class HealthController {
    @Value("${server.port}")
    private String serverPort;

    private final DiscoveryClient discoveryClient;

    @Autowired
    public HealthController(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    @GetMapping("/instance")
    MessageBox<Map<String, List<Map<String, String>>>> getInstanceInfo() {
        // 服务
        List<String> serviceList = discoveryClient.getServices();
        Map<String, List<Map<String, String>>> service2instance = new HashMap<>(serviceList.size());
        for (String service : serviceList) {
            // 实例
            List<ServiceInstance> instanceList = discoveryClient.getInstances(service);
            List<Map<String, String>> instanceInfoList = new ArrayList<>(instanceList.size());
            for (ServiceInstance instance : instanceList) {
                // 实例信息
                Map<String, String> instanceInfo = new HashMap<>(4);
                instanceInfo.put("instanceId", instance.getInstanceId());
                instanceInfo.put("instanceHost", instance.getHost());
                instanceInfo.put("instancePort", instance.getPort() + "");
                instanceInfo.put("uri", instance.getUri().toString());
                instanceInfoList.add(instanceInfo);
            }
            service2instance.put(service, instanceInfoList);
        }
        return MessageBox.ok(service2instance);
    }
}
