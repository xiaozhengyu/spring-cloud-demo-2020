package com.xzy.controller;

import com.xzy.entities.PaymentEntity;
import com.xzy.msg.Message;
import com.xzy.msg.MessageBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * 订单Controller
 *
 * @author xzy
 * @date 2022/1/4 15:51
 */
@RestController
@RequestMapping(path = "/order")
public class OrderController {
    private final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    private final RestTemplate restTemplate;

    @Autowired
    public OrderController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * 新增支付信息
     *
     * @param paymentEntity 支付信息
     * @return 1：新增成功 0：新增失败
     */
    @PostMapping
    Message save(@RequestBody PaymentEntity paymentEntity) {
        return restTemplate.postForObject(PAYMENT_URL + "/payment", paymentEntity, Message.class);
    }

    /**
     * 根据ID查找支付信息
     *
     * @param id ID
     * @return 支付信息
     */
    @GetMapping("/{id}")
    MessageBox<PaymentEntity> findByPrimaryKey(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/" + id, MessageBox.class);
    }

    @GetMapping("/health")
    MessageBox<String> getServerInfo() {
        return restTemplate.getForObject(PAYMENT_URL + "/health/service", MessageBox.class);
    }
}
