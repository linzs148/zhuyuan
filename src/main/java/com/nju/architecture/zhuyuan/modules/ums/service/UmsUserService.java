package com.nju.architecture.zhuyuan.modules.ums.service;

import com.aliyuncs.exceptions.ClientException;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nju.architecture.zhuyuan.modules.ums.dto.req.UmsUserLoginReqDTO;
import com.nju.architecture.zhuyuan.modules.ums.dto.req.UmsUserRegisterReqDTO;
import com.nju.architecture.zhuyuan.modules.ums.model.UmsUser;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author macro
 * @since 2024-05-06
 */
public interface UmsUserService extends IService<UmsUser> {

    void sendAuthCode(String phone) throws ClientException;

    boolean register(UmsUserRegisterReqDTO umsUserRegisterParam);

    String login(UmsUserLoginReqDTO umsUserRegisterParam);

    UserDetails loadUserByUsername(String username);
}
