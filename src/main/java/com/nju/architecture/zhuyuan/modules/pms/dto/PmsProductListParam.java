package com.nju.architecture.zhuyuan.modules.pms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class PmsProductListParam {

    @NotNull
    @Min(0)
    @Schema(title = "页号")
    private Integer pageNum = 0;

    @NotNull
    @Min(0)
    @Schema(title = "条目数")
    private Integer pageSize = 10;

    @Schema(title = "筛选条件")
    private List<Long> tags;

    @Schema(title = "区间下限")
    private Long minCost = 0L;

    @Schema(title = "区间上限")
    private Long maxCost = 100000L;
}
