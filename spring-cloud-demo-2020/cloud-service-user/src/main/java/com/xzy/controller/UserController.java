package com.xzy.controller;

import com.xzy.entities.UserEntity;
import com.xzy.msg.MessageBox;
import com.xzy.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户管理
 *
 * @author xzy
 * @date 2022/1/10 10:19
 */
@RestController
@RequestMapping("/user/user")
@Slf4j
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 根据主键查询用户信息
     *
     * @param id 主键
     * @return 用户信息
     */
    @GetMapping("/{id}")
    public MessageBox<UserEntity> findByPrimaryKey(@PathVariable("id") Long id) {
        return userService.findByPrimaryKey(id);
    }

    /**
     * 根据主键查询用户信息（带有服务熔断机制）
     *
     * @param id 主键
     * @return 用户信息
     */
    @GetMapping("/with_circuit_breaker/{id}")
    public MessageBox<UserEntity> findByPrimaryKeyWithCircuitBreaker(@PathVariable("id") Long id) {
        return userService.findByPrimaryKeyWithCircuitBreaker(id);
    }
}
