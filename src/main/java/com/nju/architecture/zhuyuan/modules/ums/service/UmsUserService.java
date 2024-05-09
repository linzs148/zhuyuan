package com.nju.architecture.zhuyuan.modules.ums.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nju.architecture.zhuyuan.modules.ums.dto.UmsUserLoginParam;
import com.nju.architecture.zhuyuan.modules.ums.dto.UmsUserRegisterParam;
import com.nju.architecture.zhuyuan.modules.ums.model.UmsUser;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author macro
 * @since 2024-05-06
 */
public interface UmsUserService extends IService<UmsUser> {

    boolean register(UmsUserRegisterParam umsUserRegisterParam);

    String login(UmsUserLoginParam umsUserRegisterParam);

    UserDetails loadUserByUsername(String username);
}
