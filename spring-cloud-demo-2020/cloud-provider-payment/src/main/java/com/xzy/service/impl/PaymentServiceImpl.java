package com.xzy.service.impl;

import com.xzy.entities.PaymentEntity;
import com.xzy.repository.PaymentMapper;
import com.xzy.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 支付Service
 *
 * @author xzy
 * @date 2022/1/4 15:50
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentMapper paymentMapper;

    @Autowired
    public PaymentServiceImpl(PaymentMapper paymentMapper) {
        this.paymentMapper = paymentMapper;
    }

    /**
     * 新增支付信息
     *
     * @param paymentEntity 支付信息
     * @return 1：新增成功 0：新增失败
     */
    @Override
    public int save(PaymentEntity paymentEntity) {
        return paymentMapper.save(paymentEntity);
    }

    /**
     * 根据ID查找支付信息
     *
     * @param id ID
     * @return 支付信息
     */
    @Override
    public Optional<PaymentEntity> findByPrimaryKey(Long id) {
        return paymentMapper.findByPrimaryKey(id);
    }
}
