package com.nju.architecture.zhuyuan.modules.pms.controller;

import com.nju.architecture.zhuyuan.common.api.CommonResult;
import com.nju.architecture.zhuyuan.modules.pms.dto.PmsTagResult;
import com.nju.architecture.zhuyuan.modules.pms.service.PmsTagService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "PmsTagController")
@RequestMapping("/tag")
public class PmsTagController {

    @Autowired
    private PmsTagService pmsTagService;

    /**
     * 需求列表
     */
    @GetMapping(value = "/requirements")
    @Operation(summary = "需求列表")
    public CommonResult<List<PmsTagResult>> getRequirements() {
        List<PmsTagResult> requirements = pmsTagService.getRequirements();
        return CommonResult.success(requirements);
    }

    /**
     * 类型列表
     */
    @GetMapping(value = "/types")
    @Operation(summary = "类型列表")
    public CommonResult<List<PmsTagResult>> types() {
        List<PmsTagResult> types = pmsTagService.getTypes();
        return CommonResult.success(types);
    }

}
