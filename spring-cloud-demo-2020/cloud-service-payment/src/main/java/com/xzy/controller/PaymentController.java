package com.xzy.controller;

import com.xzy.entities.UserEntity;
import com.xzy.feign.UserControllerFeign;
import com.xzy.msg.MessageBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 支付Controller
 *
 * @author xzy
 * @date 2022/1/4 15:51
 */
@RestController
@RequestMapping(path = "/payment/payment")
public class PaymentController {
    private final UserControllerFeign userControllerFeign;

    @Autowired
    public PaymentController(UserControllerFeign userControllerFeign) {
        this.userControllerFeign = userControllerFeign;
    }

    @GetMapping("/get_user_info/{user_id}")
    public MessageBox<UserEntity> getUserInfo(@PathVariable("user_id") Long userId) {
        return userControllerFeign.findByPrimaryKey(userId);
    }
}
