package com.nju.architecture.zhuyuan.modules.pms.dto;

import lombok.Data;

import java.util.List;

@Data
public class PmsProductDetailResult {
    private Long id;
    private List<String> images;
    private String creator;
    private String title;
    private String description;
    private List<String> tags;
    private Long minCost;
    private Long maxCost;
}
