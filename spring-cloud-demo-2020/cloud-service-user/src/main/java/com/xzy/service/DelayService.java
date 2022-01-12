package com.xzy.service;

import com.xzy.msg.MessageBox;

/**
 * @author xzy
 * @date 2022/1/12 17:20
 */
public interface DelayService {
    /**
     * 直接躺平：慢慢处理 + 抛出异常
     */
    MessageBox<String> delayRpo();

    /**
     * 服务降级：如果处理超时或出现异常，执行 ”Plan B“
     */
    MessageBox<String> delayRpoWithFallback();
}
