package com.nju.architecture.zhuyuan.modules.pms.controller;

import com.nju.architecture.zhuyuan.common.api.CommonResult;
import com.nju.architecture.zhuyuan.modules.pms.service.PmsOssService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@Tag(name = "PmsOssController")
@RequestMapping("/oss")
public class PmsOssController {
    @Autowired
    private PmsOssService pmsOssService;

    /**
     * 上传图片
     */
    @PostMapping(value = "/upload")
    @ResponseBody
    @Operation(summary = "上传图片")
    public CommonResult<String> upload(@RequestParam("file") MultipartFile file) {
        String url;
        try {
            url = pmsOssService.upload(file);
        } catch (IOException e) {
            return CommonResult.failed("Failed to upload picture");
        }
        return CommonResult.success(url);
    }
}