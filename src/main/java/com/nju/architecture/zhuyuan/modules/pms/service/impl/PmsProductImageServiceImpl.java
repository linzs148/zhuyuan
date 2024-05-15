package com.nju.architecture.zhuyuan.modules.pms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nju.architecture.zhuyuan.modules.pms.mapper.PmsProductImageMapper;
import com.nju.architecture.zhuyuan.modules.pms.model.PmsProductImage;
import com.nju.architecture.zhuyuan.modules.pms.service.PmsProductImageService;
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
public class PmsProductImageServiceImpl extends ServiceImpl<PmsProductImageMapper, PmsProductImage> implements PmsProductImageService {

    @Override
    public String getOneImage(Long pid) {
        QueryWrapper<PmsProductImage> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(PmsProductImage::getPid, pid);
        List<PmsProductImage> pmsProductImages = list(wrapper);
        if (pmsProductImages != null && !pmsProductImages.isEmpty()) {
            return pmsProductImages.get(0).getImage();
        }
        return null;
    }

    @Override
    public List<String> listImages(Long pid) {
        QueryWrapper<PmsProductImage> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(PmsProductImage::getPid, pid);
        List<PmsProductImage> pmsProductImages = list(wrapper);
        List<String> images = new ArrayList<>();
        for (PmsProductImage pmsProductImage : pmsProductImages) {
            images.add(pmsProductImage.getImage());
        }
        return images;
    }
}
