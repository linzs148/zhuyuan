package com.nju.architecture.zhuyuan.modules.pms.controller;

import com.nju.architecture.zhuyuan.common.api.CommonPage;
import com.nju.architecture.zhuyuan.common.api.CommonResult;
import com.nju.architecture.zhuyuan.modules.pms.dto.PmsProductDetailResult;
import com.nju.architecture.zhuyuan.modules.pms.dto.PmsProductListParam;
import com.nju.architecture.zhuyuan.modules.pms.dto.PmsProductListResult;
import com.nju.architecture.zhuyuan.modules.pms.dto.PmsProductUploadParam;
import com.nju.architecture.zhuyuan.modules.pms.service.PmsProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "PmsProductController")
@RequestMapping("/product")
public class PmsProductController {

    @Autowired
    private PmsProductService pmsProductService;

    /**
     * 上传设计
     */
    @PostMapping(value = "/upload")
    @ResponseBody
    @Operation(summary = "上传设计")
    public CommonResult<String> upload(@Validated @RequestBody PmsProductUploadParam pmsProductUploadParam) {
        try {
            pmsProductService.upload(pmsProductUploadParam);
        } catch (Exception e) {
            return CommonResult.failed(e.getMessage());
        }
        return CommonResult.success(null);
    }

    /**
     * 获取设计列表
     */
    @GetMapping(value = "/list")
    @ResponseBody
    @Operation(summary = "获取设计列表")
    public CommonResult<CommonPage<PmsProductListResult>> list(@Validated @RequestBody PmsProductListParam pmsProductListParam) {
        List<PmsProductListResult> pmsProductList;
        try {
            pmsProductList = pmsProductService.list(pmsProductListParam);
        } catch (Exception e) {
            return CommonResult.failed(e.getMessage());
        }
        CommonPage<PmsProductListResult> page = new CommonPage<>();
        page.setPageNum(pmsProductListParam.getPageNum());
        page.setPageSize(pmsProductListParam.getPageSize());
        page.setList(pmsProductList);
        return CommonResult.success(page);
    }

    /**
     * 获取设计详情
     */
    @GetMapping(value = "/detail")
    @ResponseBody
    @Operation(summary = "获取设计详情")
    public CommonResult<PmsProductDetailResult> detail(@RequestParam Long pid) {
        PmsProductDetailResult pmsProductDetailResult;
        try {
            pmsProductDetailResult = pmsProductService.get(pid);
        } catch (Exception e) {
            return CommonResult.failed(e.getMessage());
        }
        return CommonResult.success(pmsProductDetailResult);
    }

}
