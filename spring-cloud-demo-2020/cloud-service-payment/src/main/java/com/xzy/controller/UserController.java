package com.xzy.controller;

import com.xzy.entities.UserEntity;
import com.xzy.feign.UserServiceFeign;
import com.xzy.msg.MessageBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author xzy
 * @date 2022/1/4 15:51
 */
@RestController
@RequestMapping(path = "/payment/user")
public class UserController {
    private final UserServiceFeign userServiceFeign;

    @Autowired
    public UserController(UserServiceFeign userServiceFeign) {
        this.userServiceFeign = userServiceFeign;
    }

    @GetMapping("/get_user_info/{id}")
    public MessageBox<UserEntity> getUserInfo(@PathVariable("id") Long userId) {
        return userServiceFeign.findByPrimaryKey(userId);
    }

    /**
     * 根据主键查询用户信息（带有服务熔断机制）
     *
     * @param id 主键
     * @return 用户信息
     */
    @GetMapping("/get_user_info/with_circuit_breaker/{id}")
    public MessageBox<UserEntity> findByPrimaryKeyWithCircuitBreaker(@PathVariable("id") Long id) {
        return userServiceFeign.findByPrimaryKeyWithCircuitBreaker(id);
    }
}
