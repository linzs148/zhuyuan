package com.nju.architecture.zhuyuan.modules.ums.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nju.architecture.zhuyuan.domain.UmsUserDetails;
import com.nju.architecture.zhuyuan.modules.ums.dto.UmsUserLoginParam;
import com.nju.architecture.zhuyuan.modules.ums.dto.UmsUserRegisterParam;
import com.nju.architecture.zhuyuan.modules.ums.mapper.UmsUserMapper;
import com.nju.architecture.zhuyuan.modules.ums.model.UmsUser;
import com.nju.architecture.zhuyuan.modules.ums.service.UmsUserService;
import com.nju.architecture.zhuyuan.security.util.JwtTokenUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author macro
 * @since 2024-05-06
 */
@Service
public class UmsUserServiceImpl extends ServiceImpl<UmsUserMapper, UmsUser> implements UmsUserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public boolean register(UmsUserRegisterParam umsUserRegisterParam) {
        // 查询是否有相同用户名的用户
        QueryWrapper<UmsUser> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(UmsUser::getPhone, umsUserRegisterParam.getPhone());
        UmsUser umsUser = getOne(wrapper);
        if (umsUser != null) {
            return false;
        }
        umsUser = new UmsUser();
        BeanUtils.copyProperties(umsUserRegisterParam, umsUser);
        umsUser.setUsername(umsUserRegisterParam.getPhone());
        umsUser.setCreateTime(new Date());
        // 将密码进行加密操作
        String encodePassword = passwordEncoder.encode(umsUser.getPassword());
        umsUser.setPassword(encodePassword);
        save(umsUser);
        return true;
    }

    @Override
    public String login(UmsUserLoginParam umsUserLoginParam) {
        // 密码需要客户端加密后传递
        UserDetails userDetails = loadUserByUsername(umsUserLoginParam.getPhone());
        if (userDetails == null || !passwordEncoder.matches(umsUserLoginParam.getPassword(), userDetails.getPassword())) {
            return null;
        }
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenUtil.generateToken(userDetails);
        return token;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        QueryWrapper<UmsUser> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(UmsUser::getUsername, username);
        UmsUser umsUser = getOne(wrapper);
        if (umsUser == null) {
            return null;
        }
        return new UmsUserDetails(umsUser);
    }

}
