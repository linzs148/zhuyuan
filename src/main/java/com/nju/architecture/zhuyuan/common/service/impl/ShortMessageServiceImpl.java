package com.nju.architecture.zhuyuan.common.service.impl;

import com.alibaba.fastjson2.JSON;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.nju.architecture.zhuyuan.common.service.ShortMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.util.Map;
import java.util.Random;

/**
 * 短信验证码服务实现类
 * Created by linzs on 2024/5/6.
 */
@Slf4j
public class ShortMessageServiceImpl implements ShortMessageService {
    //产品名称:云通信短信API产品,开发者无需替换
    private static final String product = "Dysmsapi";

    //产品域名,开发者无需替换
    private static final String domain = "dysmsapi.aliyuncs.com";

    @Value("${aliyun.sms.sms-access-key-id}")
    private String accessKeyId;

    @Value("${aliyun.sms.sms-access-key-secret}")
    private String accessKeySecret;

    @Value("${aliyun.sms.sms-sign-nam}")
    private String signName;

    @Value("${aliyun.sms.sms-template-cod}")
    private String templateCode;

    @Override
    public SendSmsResponse sendAuthCode(String phone, String code) throws ClientException {
        //可自助调整超时时间
        //System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        //System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);
        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(phone);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName("筑园");
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode("SMS_465945853");
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        String templateParam = JSON.toJSONString(Map.of("code", code));
        request.setTemplateParam(templateParam);
        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");
        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        //request.setOutId("yourOutId");
        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = null;
        sendSmsResponse = acsClient.getAcsResponse(request);
        return sendSmsResponse;
    }

    private static final String VERIFY_CODES = "1234567890";

    /**
     * 使用系统默认字符源生成验证码
     * @param size    验证码长度
     * @return
     */
    @Override
    public String generateAuthCode(int size){
        return generateAuthCode(size, VERIFY_CODES);
    }

    /**
     * 使用指定源生成验证码
     * @param size    验证码长度
     * @param sources   验证码字符源
     * @return
     */
    private String generateAuthCode(int size, String sources){
        if(sources == null || sources.length() == 0){
            sources = VERIFY_CODES;
        }
        int codesLen = sources.length();
        Random rand = new Random(System.currentTimeMillis());
        StringBuilder authCode = new StringBuilder(size);
        for(int i = 0; i < size; i++){
            authCode.append(sources.charAt(rand.nextInt(codesLen-1)));
        }
        return authCode.toString();
    }
}
