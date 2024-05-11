package com.nju.architecture.zhuyuan.modules.ums.service.impl;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nju.architecture.zhuyuan.common.service.RedisService;
import com.nju.architecture.zhuyuan.common.service.ShortMessageService;
import com.nju.architecture.zhuyuan.domain.UmsUserDetails;
import com.nju.architecture.zhuyuan.modules.ums.dto.req.UmsUserLoginReqDTO;
import com.nju.architecture.zhuyuan.modules.ums.dto.req.UmsUserRegisterReqDTO;
import com.nju.architecture.zhuyuan.modules.ums.mapper.UmsUserMapper;
import com.nju.architecture.zhuyuan.modules.ums.model.UmsUser;
import com.nju.architecture.zhuyuan.modules.ums.service.UmsUserService;
import com.nju.architecture.zhuyuan.security.util.JwtTokenUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Autowired
    private RedisService redisService;

    @Autowired
    private ShortMessageService shortMessageService;

    @Value("${redis.key.authCode}")
    private String authCodeKey;

    @Value("${redis.expire.authCode}")
    private Long authCodeExpire;

    @Override
    public void sendAuthCode(String phone) throws ClientException {
        String code = shortMessageService.generateAuthCode(6);
        SendSmsResponse response = shortMessageService.sendAuthCode(phone, code);
        if (!"OK".equals(response.getCode())) {
            throw new RuntimeException("Failed to send auth code.");
        }
        redisService.set(authCodeKey + phone, code);
        redisService.expire(authCodeKey + phone, authCodeExpire);
    }

    @Override
    public boolean register(UmsUserRegisterReqDTO umsUserRegisterParam) {
        // 验证码
        String code = (String) redisService.get(authCodeKey + umsUserRegisterParam.getPhone());
        if (!umsUserRegisterParam.getCode().equals(code)) {
            throw new RuntimeException("Auth code dismatched.");
        }
        // 查重
        UserDetails userDetails = loadUserByUsername(umsUserRegisterParam.getPhone());
        if (userDetails != null) {
            throw new RuntimeException("Phone number has been registered.");
        }
        // 创建
        UmsUser umsUser = new UmsUser();
        BeanUtils.copyProperties(umsUserRegisterParam, umsUser);
        umsUser.setUsername(umsUserRegisterParam.getPhone());
        umsUser.setCreateTime(new Date());
        String encodePassword = passwordEncoder.encode(umsUser.getPassword());
        umsUser.setPassword(encodePassword);
        save(umsUser);
        return true;
    }

    @Override
    public String login(UmsUserLoginReqDTO umsUserLoginParam) {
        // 查询
        UserDetails userDetails = loadUserByUsername(umsUserLoginParam.getPhone());
        if (userDetails == null) {
            throw new RuntimeException("Phone number has not been registered.");
        }
        // 认证
        if (umsUserLoginParam.getPassword() != null) {
            if (!passwordEncoder.matches(umsUserLoginParam.getPassword(), userDetails.getPassword())) {
                throw new RuntimeException("Wrong password.");
            }
        } else {
            if (umsUserLoginParam.getCode() == null) {
                throw new RuntimeException("Password or Auth code must be send.");
            }
            String code = (String) redisService.get(authCodeKey + umsUserLoginParam.getPhone());
            if (!umsUserLoginParam.getCode().equals(code)) {
                throw new RuntimeException("Auth code dismatched.");
            }
        }
        // jwt
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
        return umsUser != null ? new UmsUserDetails(umsUser) : null;
    }

}
