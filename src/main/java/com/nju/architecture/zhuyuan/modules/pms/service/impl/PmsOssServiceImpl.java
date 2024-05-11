package com.nju.architecture.zhuyuan.modules.pms.service.impl;

import cn.hutool.core.util.IdUtil;
import com.aliyun.oss.OSSClient;
import com.nju.architecture.zhuyuan.modules.pms.service.PmsOssService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Slf4j
public class PmsOssServiceImpl implements PmsOssService {

    @Value("${aliyun.oss.bucket}")
    private String bucketName;

    @Autowired
    private OSSClient ossClient;

    @Override
    public String upload(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        assert fileName != null;
        String[] parts = fileName.split("\\.");
        String suffix = parts[parts.length - 1];
        fileName = IdUtil.randomUUID() + "." + suffix;
        ossClient.putObject(bucketName, fileName, file.getInputStream());
        return formatPath(fileName);
    }

    private String formatPath(String objectName) {
        return "https://" + bucketName + "." + ossClient.getEndpoint().getHost() + "/" + objectName;
    }
}