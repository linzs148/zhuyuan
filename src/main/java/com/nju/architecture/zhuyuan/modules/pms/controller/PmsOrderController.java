package com.nju.architecture.zhuyuan.modules.pms.controller;

import com.nju.architecture.zhuyuan.common.api.CommonResult;
import com.nju.architecture.zhuyuan.modules.pms.dto.PmsOrderDTO;
import com.nju.architecture.zhuyuan.modules.pms.service.PmsOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author macro
 * @since 2024-05-18
 */
@RestController
@RequestMapping("/order")
@Tag(name = "PmsOrderController")
public class PmsOrderController {

    @Autowired
    private PmsOrderService pmsOrderService;

    /**
     * 新建交易
     */
    @PostMapping(value = "/create")
    @Operation(summary = "新建交易")
    public CommonResult<Void> create(@Validated @RequestBody PmsOrderDTO pmsOrderDTO) {
        if (pmsOrderDTO.getPid() == null) {
            return CommonResult.failed("pid不能为null");
        }
        if (pmsOrderDTO.getRate1() == null || pmsOrderDTO.getRate2() == null || pmsOrderDTO.getRate3() == null) {
            return CommonResult.failed("rate不能为null");
        }
        if (pmsOrderDTO.getCost() == null) {
            return CommonResult.failed("cost不能为null");
        }
        if (pmsOrderDTO.getRate1() <= 0 || pmsOrderDTO.getRate2() <= 0 || pmsOrderDTO.getRate3() <= 0) {
            return CommonResult.failed("rate只能为正数");
        }
        if (pmsOrderDTO.getCost() <= 0) {
            return CommonResult.failed("cost只能为正数");
        }
        if (pmsOrderDTO.getRate1() + pmsOrderDTO.getRate2() + pmsOrderDTO.getRate3() != 100) {
            return CommonResult.failed("交付比例之和必须为100");
        }
        if (pmsOrderDTO.getCerator() == null) {
            return CommonResult.failed("creator不能为null");
        }
        if (pmsOrderDTO.getDesigner() == null) {
            return CommonResult.failed("designer不能为null");
        }
        try {
            pmsOrderService.create(pmsOrderDTO);
        } catch (Exception e) {
            return CommonResult.failed(e.getMessage());
        }
        return CommonResult.success(null);
    }

    /**
     * 更新交易信息
     */
    @PostMapping(value = "/update")
    @Operation(summary = "更新交易信息")
    public CommonResult<Void> update(@Validated @RequestBody PmsOrderDTO pmsOrderDTO) {
        if (pmsOrderDTO.getId() == null) {
            return CommonResult.failed("更新时id不能为空");
        }
        if (pmsOrderDTO.getRate1() != null) {
            if (pmsOrderDTO.getRate2() == null || pmsOrderDTO.getRate3() == null) {
                return CommonResult.failed("rate不能为null");
            }
            if (pmsOrderDTO.getRate1() <= 0 || pmsOrderDTO.getRate2() <= 0 || pmsOrderDTO.getRate3() <= 0) {
                return CommonResult.failed("rate只能为正数");
            }
            if (pmsOrderDTO.getRate1() + pmsOrderDTO.getRate2() + pmsOrderDTO.getRate3() != 100) {
                return CommonResult.failed("交付比例之和必须为100");
            }
        }
        if (pmsOrderDTO.getCost() != null) {
            if (pmsOrderDTO.getCost() <= 0) {
                return CommonResult.failed("cost只能为正数");
            }
        }
        try {
            pmsOrderService.update(pmsOrderDTO);
        } catch (Exception e) {
            return CommonResult.failed(e.getMessage());
        }
        return CommonResult.success(null);
    }

    /**
     * 获取交易信息
     */
    @GetMapping(value = "/detail")
    @Operation(summary = "获取交易信息")
    public CommonResult<PmsOrderDTO> get(@RequestBody Map<String, Object> params) {
        Long id = Long.valueOf(params.get("id").toString());
        PmsOrderDTO pmsOrderDTO;
        try {
            pmsOrderDTO = pmsOrderService.get(id);
        } catch (Exception e) {
            return CommonResult.failed(e.getMessage());
        }
        return CommonResult.success(pmsOrderDTO);
    }

}
