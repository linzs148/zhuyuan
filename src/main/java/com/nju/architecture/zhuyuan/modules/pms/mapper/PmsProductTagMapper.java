package com.nju.architecture.zhuyuan.modules.pms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nju.architecture.zhuyuan.modules.pms.model.PmsProductTag;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author macro
 * @since 2024-05-11
 */
public interface PmsProductTagMapper extends BaseMapper<PmsProductTag> {

    List<String> getTagList(Long pid);

}
