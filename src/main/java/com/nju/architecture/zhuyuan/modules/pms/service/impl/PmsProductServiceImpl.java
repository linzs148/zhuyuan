package com.nju.architecture.zhuyuan.modules.pms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nju.architecture.zhuyuan.domain.UmsUserDetails;
import com.nju.architecture.zhuyuan.modules.pms.dto.PmsProductDetailResult;
import com.nju.architecture.zhuyuan.modules.pms.dto.PmsProductListParam;
import com.nju.architecture.zhuyuan.modules.pms.dto.PmsProductListResult;
import com.nju.architecture.zhuyuan.modules.pms.dto.PmsProductUploadParam;
import com.nju.architecture.zhuyuan.modules.pms.mapper.PmsProductMapper;
import com.nju.architecture.zhuyuan.modules.pms.model.PmsProduct;
import com.nju.architecture.zhuyuan.modules.pms.model.PmsProductImage;
import com.nju.architecture.zhuyuan.modules.pms.model.PmsProductTag;
import com.nju.architecture.zhuyuan.modules.pms.service.PmsProductImageService;
import com.nju.architecture.zhuyuan.modules.pms.service.PmsProductService;
import com.nju.architecture.zhuyuan.modules.pms.service.PmsProductTagService;
import com.nju.architecture.zhuyuan.modules.ums.mapper.UmsUserMapper;
import com.nju.architecture.zhuyuan.modules.ums.model.UmsUser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class PmsProductServiceImpl extends ServiceImpl<PmsProductMapper, PmsProduct> implements PmsProductService {

    @Autowired
    private PmsProductImageService pmsProductImageService;

    @Autowired
    private PmsProductTagService pmsProductTagService;

    @Autowired
    private UmsUserMapper umsUserMapper;

    @Override
    @Transactional
    public void upload(PmsProductUploadParam pmsProductUploadParam) {
        // user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UmsUserDetails umsUserDetails = (UmsUserDetails) authentication.getPrincipal();
        Long uid = umsUserDetails.getId();
        // product
        PmsProduct pmsProduct = new PmsProduct();
        pmsProduct.setCreator(uid);
        BeanUtils.copyProperties(pmsProductUploadParam, pmsProduct);
        save(pmsProduct);
        Long pid = pmsProduct.getId();
        // images
        List<PmsProductImage> pmsProductImages = new ArrayList<>();
        for (String image : pmsProductUploadParam.getImages()) {
            PmsProductImage pmsProductImage = new PmsProductImage();
            pmsProductImage.setPid(pid);
            pmsProductImage.setImage(image);
            pmsProductImages.add(pmsProductImage);
        }
        pmsProductImageService.saveBatch(pmsProductImages);
        // tags
        List<PmsProductTag> pmsProductTags = new ArrayList<>();
        for (Long tid : pmsProductUploadParam.getTags()) {
            PmsProductTag pmsProductTag = new PmsProductTag();
            pmsProductTag.setPid(pid);
            pmsProductTag.setTid(tid);
            pmsProductTags.add(pmsProductTag);
        }
        pmsProductTagService.saveBatch(pmsProductTags);
    }

    @Override
    public List<PmsProductListResult> list(PmsProductListParam pmsProductListParam) {
        List<PmsProductListResult> products = baseMapper.getProductList(pmsProductListParam.getTags(), pmsProductListParam.getMinCost(), pmsProductListParam.getMaxCost(), pmsProductListParam.getPageSize(), pmsProductListParam.getPageNum() * pmsProductListParam.getPageSize());
        for (PmsProductListResult product : products) {
            Long creatorId = Long.parseLong(product.getCreator());
            UmsUser creator = umsUserMapper.selectById(creatorId);
            if (creator != null) {
                product.setCreator(creator.getUsername());
            }
            String image = pmsProductImageService.getOneImage(product.getId());
            product.setImage(image);
            List<String> tags = pmsProductTagService.getTagList(product.getId());
            product.setTags(tags);
        }
        return products;
    }

    @Override
    public PmsProductDetailResult get(Long pid) {
        PmsProductDetailResult productDetailResult = new PmsProductDetailResult();
        PmsProduct pmsProduct = getById(pid);
        BeanUtils.copyProperties(pmsProduct, productDetailResult);
        UmsUser creator = umsUserMapper.selectById(pmsProduct.getCreator());
        if (creator != null) {
            productDetailResult.setCreator(creator.getUsername());
        }
        List<String> images = pmsProductImageService.listImages(pid);
        productDetailResult.setImages(images);
        List<String> tags = pmsProductTagService.getTagList(pid);
        productDetailResult.setTags(tags);
        return productDetailResult;
    }
}
