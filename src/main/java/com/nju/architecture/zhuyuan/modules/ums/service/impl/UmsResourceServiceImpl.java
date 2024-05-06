package com.nju.architecture.zhuyuan.modules.ums.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nju.architecture.zhuyuan.modules.ums.mapper.UmsResourceMapper;
import com.nju.architecture.zhuyuan.modules.ums.model.UmsResource;
import com.nju.architecture.zhuyuan.modules.ums.service.UmsResourceService;
import org.springframework.stereotype.Service;

/**
 * 后台资源管理Service实现类
 * Created by macro on 2020/2/2.
 */
@Service
public class UmsResourceServiceImpl extends ServiceImpl<UmsResourceMapper,UmsResource>implements UmsResourceService {
    @Override
    public Page<UmsResource> list(Long categoryId, String nameKeyword, String urlKeyword, Integer pageSize, Integer pageNum) {
        Page<UmsResource> page = new Page<>(pageNum,pageSize);
        QueryWrapper<UmsResource> wrapper = new QueryWrapper<>();
        LambdaQueryWrapper<UmsResource> lambda = wrapper.lambda();
        if(categoryId!=null){
            lambda.eq(UmsResource::getCategoryId,categoryId);
        }
        if(StrUtil.isNotEmpty(nameKeyword)){
            lambda.like(UmsResource::getName,nameKeyword);
        }
        if(StrUtil.isNotEmpty(urlKeyword)){
            lambda.like(UmsResource::getUrl,urlKeyword);
        }
        return page(page,wrapper);
    }
}
