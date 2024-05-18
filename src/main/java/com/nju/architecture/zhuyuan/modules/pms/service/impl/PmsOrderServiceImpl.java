package com.nju.architecture.zhuyuan.modules.pms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nju.architecture.zhuyuan.modules.pms.dto.PmsOrderDTO;
import com.nju.architecture.zhuyuan.modules.pms.mapper.PmsOrderMapper;
import com.nju.architecture.zhuyuan.modules.pms.model.PmsOrder;
import com.nju.architecture.zhuyuan.modules.pms.model.PmsOrderImage;
import com.nju.architecture.zhuyuan.modules.pms.service.PmsOrderImageService;
import com.nju.architecture.zhuyuan.modules.pms.service.PmsOrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author macro
 * @since 2024-05-18
 */
@Service
public class PmsOrderServiceImpl extends ServiceImpl<PmsOrderMapper, PmsOrder> implements PmsOrderService {

    @Autowired
    private PmsOrderImageService pmsOrderImageService;

    private void saveImages(PmsOrderDTO pmsOrderDTO, Long oid) {
        List<PmsOrderImage> pmsOrderImages = new ArrayList<>();
        if (pmsOrderDTO.getImages1() != null) {
            for (String image : pmsOrderDTO.getImages1()) {
                PmsOrderImage pmsOrderImage = new PmsOrderImage();
                pmsOrderImage.setOid(oid);
                pmsOrderImage.setType(1);
                pmsOrderImage.setImage(image);
                pmsOrderImages.add(pmsOrderImage);
            }
        }
        if (pmsOrderDTO.getImages2() != null) {
            for (String image : pmsOrderDTO.getImages2()) {
                PmsOrderImage pmsOrderImage = new PmsOrderImage();
                pmsOrderImage.setOid(oid);
                pmsOrderImage.setType(2);
                pmsOrderImage.setImage(image);
                pmsOrderImages.add(pmsOrderImage);
            }
        }
        pmsOrderImageService.saveBatch(pmsOrderImages);
    }

    @Override
    @Transactional
    public void create(PmsOrderDTO pmsOrderDTO) {
        PmsOrder pmsOrder = new PmsOrder();
        BeanUtils.copyProperties(pmsOrderDTO, pmsOrder);
        pmsOrder.setState(1);
        save(pmsOrder);
        saveImages(pmsOrderDTO, pmsOrder.getId());
    }

    @Override
    @Transactional
    public void update(PmsOrderDTO pmsOrderDTO) {
        PmsOrder pmsOrder = new PmsOrder();
        BeanUtils.copyProperties(pmsOrderDTO, pmsOrder);
        updateById(pmsOrder);
        Map<String, Object> preds = new HashMap<>();
        preds.put("oid", pmsOrder.getId());
        pmsOrderImageService.removeByMap(preds);
        saveImages(pmsOrderDTO, pmsOrder.getId());
    }

    @Override
    public PmsOrderDTO get(Long id) {
        PmsOrderDTO pmsOrderDTO = new PmsOrderDTO();
        PmsOrder pmsOrder = getById(id);
        BeanUtils.copyProperties(pmsOrder, pmsOrderDTO);
        QueryWrapper<PmsOrderImage> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(PmsOrderImage::getOid, id);
        List<PmsOrderImage> pmsOrderImages = pmsOrderImageService.list(wrapper);
        List<String> images1 = new ArrayList<>();
        List<String> images2 = new ArrayList<>();
        for (PmsOrderImage pmsOrderImage : pmsOrderImages) {
            if (pmsOrderImage.getType() == 1) {
                images1.add(pmsOrderImage.getImage());
            } else {
                images2.add(pmsOrderImage.getImage());
            }
        }
        pmsOrderDTO.setImages1(images1);
        pmsOrderDTO.setImages2(images2);
        return pmsOrderDTO;
    }
}
