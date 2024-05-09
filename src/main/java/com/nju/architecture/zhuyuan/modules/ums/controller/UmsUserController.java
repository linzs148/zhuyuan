package com.nju.architecture.zhuyuan.modules.ums.controller;

import com.aliyuncs.exceptions.ClientException;
import com.nju.architecture.zhuyuan.common.api.CommonResult;
import com.nju.architecture.zhuyuan.modules.ums.dto.UmsUserLoginParam;
import com.nju.architecture.zhuyuan.modules.ums.dto.UmsUserRegisterParam;
import com.nju.architecture.zhuyuan.modules.ums.service.UmsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author linzs
 * @since 2024-05-06
 */
@RestController
@RequestMapping("/user")
public class UmsUserController {

    @Autowired
    private UmsUserService umsUserService;

    /**
     * 发送短信
     */
    @ResponseBody
    @PostMapping(value = "/sendAuthCode")
    public CommonResult<Void> sendAuthCode(@RequestBody Map<String, String> params) {
        String phone = params.get("phone");
        try {
            umsUserService.sendAuthCode(phone);
        } catch (RuntimeException | ClientException e) {
            return CommonResult.failed(e.getMessage());
        }
        return CommonResult.success(null);
    }

    /**
     * 注册账号
     */
    @ResponseBody
    @PostMapping(value = "/register")
    public CommonResult<Void> register(@Validated @RequestBody UmsUserRegisterParam umsUserRegisterParam) {
        try {
            umsUserService.register(umsUserRegisterParam);
        } catch (RuntimeException e) {
            return CommonResult.failed(e.getMessage());
        }
        return CommonResult.success(null);
    }

    /**
     * 登录
     */
    @ResponseBody
    @PostMapping(value = "/login")
    public CommonResult<String> login(@Validated @RequestBody UmsUserLoginParam umsUserLoginParam) {
        String token;
        try {
            token = umsUserService.login(umsUserLoginParam);
        } catch (RuntimeException e) {
            return CommonResult.failed(e.getMessage());
        }
        return CommonResult.success(token);
    }

//
//    /**
//     * 微信登陆
//     */
//    @ResponseBody
//    @PostMapping(value = "/login/wechat")
//    public CommonResult<String> login(@Validated @RequestBody UmsUserLoginParam umsUserLoginParam) {
//        return CommonResult.success(null);
//    }
}
