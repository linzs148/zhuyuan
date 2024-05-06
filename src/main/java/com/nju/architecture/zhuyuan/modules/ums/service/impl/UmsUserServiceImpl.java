package com.nju.architecture.zhuyuan.modules.ums.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nju.architecture.zhuyuan.modules.ums.dto.UmsUserRegisterParam;
import com.nju.architecture.zhuyuan.modules.ums.model.UmsUser;
import com.nju.architecture.zhuyuan.modules.ums.mapper.UmsUserMapper;
import com.nju.architecture.zhuyuan.modules.ums.service.UmsUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author macro
 * @since 2024-05-06
 */
@Service
public class UmsUserServiceImpl extends ServiceImpl<UmsUserMapper, UmsUser> implements UmsUserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UmsUser register(UmsUserRegisterParam umsUserRegisterParam) {
        UmsUser umsUser = new UmsUser();
        BeanUtils.copyProperties(umsUserRegisterParam, umsUser);
        umsUser.setCreateTime(new Date());
        //查询是否有相同用户名的用户
        QueryWrapper<UmsUser> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(UmsUser::getPhone,umsUser.getPhone());
        List<UmsUser> umsAdminList = list(wrapper);
        if (!umsAdminList.isEmpty()) {
            return null;
        }
        //将密码进行加密操作
        String encodePassword = passwordEncoder.encode(umsUser.getPassword());
        umsUser.setPassword(encodePassword);
        baseMapper.insert(umsUser);
        return umsUser;
    }

}
