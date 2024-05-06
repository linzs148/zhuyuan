package com.nju.architecture.zhuyuan.common.service;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;

/**
 * 短信验证码服务
 * Created by linzs on 2024/5/6.
 */
public interface ShortMessageService {
    SendSmsResponse sendAuthCode(String phone, String code) throws ClientException;

    String generateAuthCode(int size);
}
