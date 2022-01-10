package com.xzy.service.impl;

import com.xzy.entities.UserEntity;
import com.xzy.service.UserService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户管理
 *
 * @author xzy
 * @date 2022/1/10 10:11
 */
@Service
public class UserServiceImpl implements UserService {
    private final Map<Long, UserEntity> TEST_RECORDS = new HashMap<>(3);

    {
        TEST_RECORDS.put(1001L, new UserEntity(1001L, "张三", "男", 21));
        TEST_RECORDS.put(1002L, new UserEntity(1002L, "李四", "女", 22));
        TEST_RECORDS.put(1003L, new UserEntity(1003L, "王五", "男", 23));
    }

    /**
     * 根据主键查询用户信息
     *
     * @param id 主键
     * @return 用户信息
     */
    @Override
    public UserEntity findByPrimaryKey(Long id) {
        return TEST_RECORDS.get(id);
    }
}
