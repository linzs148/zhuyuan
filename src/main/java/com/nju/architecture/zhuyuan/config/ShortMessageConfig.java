package com.nju.architecture.zhuyuan.config;

import com.nju.architecture.zhuyuan.common.service.ShortMessageService;
import com.nju.architecture.zhuyuan.common.service.impl.ShortMessageServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 短信服务配置
 * Created by linzs on 2024/5/6.
 */
@Configuration
public class ShortMessageConfig {
    @Bean
    public ShortMessageService shortMessageService() {
        return new ShortMessageServiceImpl();
    }
}
