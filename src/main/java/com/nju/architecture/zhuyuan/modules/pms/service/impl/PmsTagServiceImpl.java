package com.nju.architecture.zhuyuan.modules.pms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nju.architecture.zhuyuan.modules.pms.dto.PmsTagResult;
import com.nju.architecture.zhuyuan.modules.pms.mapper.PmsTagMapper;
import com.nju.architecture.zhuyuan.modules.pms.model.PmsTag;
import com.nju.architecture.zhuyuan.modules.pms.service.PmsTagService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
public class PmsTagServiceImpl extends ServiceImpl<PmsTagMapper, PmsTag> implements PmsTagService {

    @Override
    public List<PmsTagResult> getRequirements() {
        return list(1);
    }

    @Override
    public List<PmsTagResult> getTypes() {
        return list(2);
    }

    private List<PmsTagResult> list(int typeId) {
        QueryWrapper<PmsTag> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(PmsTag::getType, typeId);
        List<PmsTag> tags = list(wrapper);
        List<PmsTagResult> results = new ArrayList<>();
        for (PmsTag tag : tags) {
            PmsTagResult result = new PmsTagResult();
            result.setId(tag.getId());
            result.setName(tag.getName());
            results.add(result);
        }
        return results;
    }

}
