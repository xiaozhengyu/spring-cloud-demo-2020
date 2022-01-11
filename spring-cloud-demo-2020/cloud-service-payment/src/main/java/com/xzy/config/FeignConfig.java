package com.xzy.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 设置：Feign
 *
 * @author xzy
 * @date 2022/1/11 14:56
 */
@Configuration
public class FeignConfig {

    /**
     * feign 日志级别
     */
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
