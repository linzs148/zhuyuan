package com.nju.architecture.zhuyuan.modules.pms.service;

import com.nju.architecture.zhuyuan.modules.pms.dto.PmsProductDetailResult;
import com.nju.architecture.zhuyuan.modules.pms.dto.PmsProductListParam;
import com.nju.architecture.zhuyuan.modules.pms.dto.PmsProductListResult;
import com.nju.architecture.zhuyuan.modules.pms.dto.PmsProductUploadParam;

import java.util.List;

public interface PmsProductService {

    void upload(PmsProductUploadParam pmsProductUploadParam);

    List<PmsProductListResult> list(PmsProductListParam pmsProductListParam);

    PmsProductDetailResult get(Long pid);
}
