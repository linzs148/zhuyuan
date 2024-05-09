package com.nju.architecture.zhuyuan.config;

import com.nju.architecture.zhuyuan.modules.ums.service.UmsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * zhuyuan-security模块相关配置
 * 自定义配置，用于配置如何获取用户信息及动态权限
 * Created by macro on 2019/11/9.
 */
@Configuration
public class ZhuyuanSecurityConfig {

    @Autowired
    private UmsUserService umsUserService;

    @Bean
    public UserDetailsService userDetailsService() {
        // 获取登录用户信息
        return username -> umsUserService.loadUserByUsername(username);
    }

}
