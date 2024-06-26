package com.nju.architecture.zhuyuan.modules.ums.controller;

import com.aliyuncs.exceptions.ClientException;
import com.nju.architecture.zhuyuan.common.api.CommonResult;
import com.nju.architecture.zhuyuan.modules.ums.dto.req.AuthCodeReqDTO;
import com.nju.architecture.zhuyuan.modules.ums.dto.req.UmsUserLoginReqDTO;
import com.nju.architecture.zhuyuan.modules.ums.dto.req.UmsUserRegisterReqDTO;
import com.nju.architecture.zhuyuan.modules.ums.service.UmsUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author linzs
 * @since 2024-05-06
 */
@Tag(name = "UmsUserController")
@RestController
@RequestMapping("/user")
public class UmsUserController {

    @Autowired
    private UmsUserService umsUserService;

    /**
     * 发送短信
     */
    @Operation(summary = "发送短信")
    @PostMapping(value = "/sendAuthCode")
    public CommonResult<Void> sendAuthCode(@Validated @RequestBody AuthCodeReqDTO authCodeReqDTO) {
        try {
            umsUserService.sendAuthCode(authCodeReqDTO.getPhone());
        } catch (RuntimeException | ClientException e) {
            return CommonResult.failed(e.getMessage());
        }
        return CommonResult.success(null);
    }

    /**
     * 注册账号
     */
    @Operation(summary = "注册账号")
    @PostMapping(value = "/register")
    public CommonResult<Void> register(@Validated @RequestBody UmsUserRegisterReqDTO umsUserRegisterParam) {
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
    @Operation(summary = "登陆")
    @PostMapping(value = "/login")
    public CommonResult<String> login(@Validated @RequestBody UmsUserLoginReqDTO umsUserLoginParam) {
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
