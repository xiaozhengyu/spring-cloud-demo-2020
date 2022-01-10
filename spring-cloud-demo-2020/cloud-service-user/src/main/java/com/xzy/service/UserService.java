package com.xzy.service;

import com.xzy.entities.UserEntity;

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
    UserEntity findByPrimaryKey(Long id);
}
