package com.xzy.service;

import com.xzy.entities.UserEntity;
import com.xzy.msg.MessageBox;

/**
 * 用户管理
 *
 * @author xzy
 * @date 2022/1/10 10:11
 */
public interface UserService {
    /**
     * 根据主键查询用户信息
     *
     * @param id 主键
     * @return 用户信息
     */
    MessageBox<UserEntity> findByPrimaryKey(Long id);

    /**
     * 根据主键查询用户信息（带有服务熔断机制）
     *
     * @param id 主键
     * @return 用户信息
     */
    MessageBox<UserEntity> findByPrimaryKeyWithCircuitBreaker(Long id);
}
