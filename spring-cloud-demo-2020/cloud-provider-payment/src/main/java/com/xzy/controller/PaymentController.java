package com.xzy.controller;

import com.xzy.entities.PaymentEntity;
import com.xzy.msg.Message;
import com.xzy.msg.MessageBox;
import com.xzy.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

;

/**
 * 支付Controller
 *
 * @author xzy
 * @date 2022/1/4 15:51
 */
@RestController
@RequestMapping(path = "/payment")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    /**
     * 新增支付信息
     *
     * @param paymentEntity 支付信息
     * @return 1：新增成功 0：新增失败
     */
    @PostMapping
    Message save(@RequestBody PaymentEntity paymentEntity) {
        if (1 == paymentService.save(paymentEntity)) {
            return Message.ok();
        } else {
            return Message.fail();
        }
    }

    /**
     * 根据ID查找支付信息
     *
     * @param id ID
     * @return 支付信息
     */
    @GetMapping("/{id}")
    MessageBox<PaymentEntity> findByPrimaryKey(@PathVariable("id") Long id) {
        Optional<PaymentEntity> paymentEntityOptional = paymentService.findByPrimaryKey(id);
        return paymentEntityOptional
                .map(MessageBox::ok)
                .orElseGet(() -> MessageBox.fail(Message.MESSAGE_FAIL, null));
    }
}
