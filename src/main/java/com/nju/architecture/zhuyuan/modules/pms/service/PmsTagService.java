package com.nju.architecture.zhuyuan.modules.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nju.architecture.zhuyuan.modules.pms.dto.PmsTagResult;
import com.nju.architecture.zhuyuan.modules.pms.model.PmsTag;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author macro
 * @since 2024-05-11
 */
public interface PmsTagService extends IService<PmsTag> {

    List<PmsTagResult> getRequirements();

    List<PmsTagResult> getTypes();

}
