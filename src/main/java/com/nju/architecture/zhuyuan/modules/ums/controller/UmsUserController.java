package com.nju.architecture.zhuyuan.modules.ums.controller;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.nju.architecture.zhuyuan.common.api.CommonResult;
import com.nju.architecture.zhuyuan.common.service.RedisService;
import com.nju.architecture.zhuyuan.common.service.ShortMessageService;
import com.nju.architecture.zhuyuan.modules.ums.dto.UmsUserRegisterParam;
import com.nju.architecture.zhuyuan.modules.ums.model.UmsUser;
import com.nju.architecture.zhuyuan.modules.ums.service.UmsUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    private RedisService redisService;

    @Autowired
    private ShortMessageService shortMessageService;

    @Value("${redis.key.authCode}")
    private String authCodeKey;

    @Value("${redis.expire.authCode}")
    private Long authCodeExpire;

    @Autowired
    private UmsUserService userService;

    /**
     * 发送短信
     */
    @ResponseBody
    @PostMapping(value = "/code")
    public CommonResult<Void> sendAuthCode(@RequestBody Map<String, String> params) {
        String phone = params.get("phone");
        if (phone == null || phone.length() != 11 || !StringUtils.isNumeric(phone)) {
            return CommonResult.failed("Phone number is invalid.");
        }
        String code = shortMessageService.generateAuthCode(6);
        SendSmsResponse response;
        try {
            response = shortMessageService.sendAuthCode(phone, code);
        } catch (ClientException e) {
            return CommonResult.failed("Failed to send auth code.");
        }
        if (!"OK".equals(response.getCode())) {
            return CommonResult.failed("Failed to send auth code.");
        }
        redisService.set(authCodeKey + phone, code);
        redisService.expire(authCodeKey + phone, authCodeExpire);
        return CommonResult.success(null);
    }

    /**
     * 注册账号
     */
    @ResponseBody
    @PostMapping(value = "/register")
    public CommonResult<Void> register(@Validated @RequestBody UmsUserRegisterParam umsUserRegisterParam) {
        String code = (String) redisService.get(authCodeKey + umsUserRegisterParam.getPhone());
        if (!umsUserRegisterParam.getCode().equals(code)) {
            return CommonResult.failed("Auth code dismatched.");
        }
        UmsUser umsUser = userService.register(umsUserRegisterParam);
        if (umsUser == null) {
            return CommonResult.failed("Phone number has been registered.");
        }
        return CommonResult.success(null);
    }
}
