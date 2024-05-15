package com.nju.architecture.zhuyuan.config;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OssConfig {

    @Value("${aliyun.oss.endpoint}")
    String endpoint;

    @Value("${aliyun.access-key}")
    String accessKeyId;

    @Value("${aliyun.access-secret}")
    String accessKeySecret;

    @Bean
    public OSSClient createOssClient() {
        return (OSSClient) new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
    }
}