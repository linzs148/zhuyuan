package com.nju.architecture.zhuyuan.modules.pms.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface PmsOssService {
    String upload(MultipartFile file) throws IOException;

}
