package com.nju.architecture.zhuyuan.modules.pms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class PmsProductUploadParam {

    @NotEmpty
    @Schema(title = "标题")
    private String title;

    @NotEmpty
    @Schema(title = "描述")
    private String description;

    @Schema(title = "图片")
    private List<String> images;

    @Schema(title = "标签")
    private List<Long> tags;

    @NotNull
    @Schema(title = "区间下限")
    private Long minCost;

    @NotNull
    @Schema(title = "区间上限")
    private Long maxCost;
}
