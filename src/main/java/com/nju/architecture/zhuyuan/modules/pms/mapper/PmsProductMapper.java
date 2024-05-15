package com.nju.architecture.zhuyuan.modules.pms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nju.architecture.zhuyuan.modules.pms.dto.PmsProductListResult;
import com.nju.architecture.zhuyuan.modules.pms.model.PmsProduct;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author macro
 * @since 2024-05-11
 */
public interface PmsProductMapper extends BaseMapper<PmsProduct> {

    List<PmsProductListResult> getProductList(List<Long> tags, Long minCost, Long maxCost, Integer limit, Integer offset);

}
