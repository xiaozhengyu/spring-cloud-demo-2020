package com.xzy.controller;

import com.xzy.msg.MessageBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 支付Controller
 *
 * @author xzy
 * @date 2022/1/4 15:51
 */
@RestController
@RequestMapping(path = "/payment/payment")
public class PaymentController {
    public static final String INVOKE_URL = "http://cloud-user-service";

    private final RestTemplate restTemplate;

    @Autowired
    public PaymentController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/get_user_info")
    public MessageBox<Object> getUserInfo(@RequestParam("user_id") Long userId) {
        /*ParameterizedTypeReference<MessageBox<Object>> typeReference = new ParameterizedTypeReference<MessageBox<Object>>() {};
        ResponseEntity<MessageBox<Object>> responseEntity = restTemplate.exchange(INVOKE_URL + "/user/user/" + userId, HttpMethod.GET, null, typeReference);
        return responseEntity.getBody();*/
        
        return restTemplate.getForObject(INVOKE_URL + "/user/user/" + userId, MessageBox.class);
    }
}
