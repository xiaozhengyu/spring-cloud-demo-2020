package com.xzy.repository;

import com.xzy.entities.PaymentEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 支付Dao
 *
 * @author xzy
 * @date 2022/1/4 15:46
 */
@Repository
@Mapper
public interface PaymentMapper {
    /**
     * 新增支付信息
     *
     * @param paymentEntity 支付信息
     * @return 1：新增成功 0：新增失败
     */
    int save(PaymentEntity paymentEntity);

    /**
     * 根据ID查找支付信息
     *
     * @param id ID
     * @return 支付信息
     */
    Optional<PaymentEntity> findByPrimaryKey(@Param("id") Long id);
}
