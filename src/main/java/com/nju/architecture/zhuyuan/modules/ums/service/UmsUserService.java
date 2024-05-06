package com.nju.architecture.zhuyuan.modules.ums.service;

import com.nju.architecture.zhuyuan.modules.ums.dto.UmsUserRegisterParam;
import com.nju.architecture.zhuyuan.modules.ums.model.UmsUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author macro
 * @since 2024-05-06
 */
public interface UmsUserService extends IService<UmsUser> {

    UmsUser register(UmsUserRegisterParam umsUserRegisterParam);
}
