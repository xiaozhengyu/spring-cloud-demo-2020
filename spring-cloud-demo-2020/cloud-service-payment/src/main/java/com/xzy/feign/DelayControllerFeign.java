package com.xzy.feign;

import com.xzy.msg.MessageBox;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author xzy
 * @date 2022/1/12 15:16
 */
@Service
@FeignClient("cloud-user-service")
public interface DelayControllerFeign {

    /**
     * 处理时间很慢的RPO
     */
    @GetMapping("/user/delay/delay")
    MessageBox<String> delayRpo();
}
