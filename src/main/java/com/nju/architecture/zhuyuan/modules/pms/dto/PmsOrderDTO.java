package com.nju.architecture.zhuyuan.modules.pms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class PmsOrderDTO {

    @Schema(title = "id")
    private Long id;

    @Schema(title = "项目id")
    private Long pid;

    @Schema(title = "阶段一描述")
    private String description1;

    @Schema(title = "阶段一交付比例")
    private Integer rate1;

    @Schema(title = "阶段二描述")
    private String description2;

    @Schema(title = "阶段二交付比例")
    private Integer rate2;

    @Schema(title = "阶段二描述")
    private String description3;

    @Schema(title = "阶段三交付比例")
    private Integer rate3;

    @Schema(title = "客户方")
    private String cerator;

    @Schema(title = "设计师")
    private String designer;

    @NotEmpty
    @Schema(title = "详细描述")
    private String description;

    @Schema(title = "状态")
    private Integer state;

    @Schema(title = "订单总价")
    private Long cost;

    @Schema(title = "意向图")
    private List<String> images1;

    @Schema(title = "现场图")
    private List<String> images2;
}
