package com.nju.architecture.zhuyuan.modules.pms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nju.architecture.zhuyuan.modules.pms.mapper.PmsProductTagMapper;
import com.nju.architecture.zhuyuan.modules.pms.model.PmsProductTag;
import com.nju.architecture.zhuyuan.modules.pms.service.PmsProductTagService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author macro
 * @since 2024-05-11
 */
@Service
public class PmsProductTagServiceImpl extends ServiceImpl<PmsProductTagMapper, PmsProductTag> implements PmsProductTagService {

    @Override
    public List<String> getTagList(Long pid) {
        return baseMapper.getTagList(pid);
    }
}
