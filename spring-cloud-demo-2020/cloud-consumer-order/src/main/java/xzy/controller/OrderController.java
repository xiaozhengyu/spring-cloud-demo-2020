package xzy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import xzy.common.msg.Message;
import xzy.common.msg.MessageBox;
import xzy.entities.PaymentEntity;

/**
 * 订单Controller
 *
 * @author xzy
 * @date 2022/1/4 15:51
 */
@RestController
@RequestMapping(path = "/order")
public class OrderController {
    private final String PAYMENT_URL = "http://127.0.0.1:8001/payment";

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
        return restTemplate.postForObject(PAYMENT_URL, paymentEntity, Message.class);
    }

    /**
     * 根据ID查找支付信息
     *
     * @param id ID
     * @return 支付信息
     */
    @GetMapping("/{id}")
    MessageBox<PaymentEntity> findByPrimaryKey(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/" + id, MessageBox.class);
    }
}
