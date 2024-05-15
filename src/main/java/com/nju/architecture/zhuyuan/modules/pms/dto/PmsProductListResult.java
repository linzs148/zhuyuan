package com.nju.architecture.zhuyuan.modules.pms.dto;

import lombok.Data;

import java.util.List;

@Data
public class PmsProductListResult {
    private Long id;
    private String image;
    private String creator;
    private String title;
    private List<String> tags;
    private Long minCost;
    private Long maxCost;
}
