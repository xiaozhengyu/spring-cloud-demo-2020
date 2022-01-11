package com.xzy.feign;

import com.xzy.entities.UserEntity;
import com.xzy.msg.MessageBox;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author xzy
 * @date 2022/1/11 14:10
 */
@Service
@FeignClient("cloud-user-service")
public interface UserControllerFeign {

    /**
     * 根据主键查询用户信息
     *
     * @param id 主键
     * @return 用户信息
     */
    @GetMapping("/user/user/{id}") // 注意这个地址！
    MessageBox<UserEntity> findByPrimaryKey(@PathVariable("id") Long id);
}
