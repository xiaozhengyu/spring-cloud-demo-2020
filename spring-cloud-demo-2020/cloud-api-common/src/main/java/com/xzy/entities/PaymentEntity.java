package com.xzy.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 支付实体
 *
 * @author xzy
 * @date 2022/1/4 15:43
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class PaymentEntity implements Serializable {

    private static final long serialVersionUID = 4252962612719401808L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 流水号
     */
    private String serial;
}
