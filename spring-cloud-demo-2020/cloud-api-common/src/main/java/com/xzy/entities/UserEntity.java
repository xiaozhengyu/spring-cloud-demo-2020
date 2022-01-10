package com.xzy.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 用户信息
 *
 * @author xzy
 * @date 2022/1/10 9:55
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity implements Serializable {

    private static final long serialVersionUID = -1753663566807763438L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private String sex;

    /**
     * 年龄
     */
    private Integer age;

}
