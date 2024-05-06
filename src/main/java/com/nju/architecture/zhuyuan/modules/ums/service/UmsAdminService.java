package com.nju.architecture.zhuyuan.modules.ums.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nju.architecture.zhuyuan.modules.ums.model.UmsAdmin;
import com.nju.architecture.zhuyuan.modules.ums.model.UmsResource;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * 后台管理员管理Service
 * Created by macro on 2018/4/26.
 */
public interface UmsAdminService extends IService<UmsAdmin> {
    /**
     * 根据用户名获取后台管理员
     */
    UmsAdmin getAdminByUsername(String username);

    /**
     * 根据用户名或昵称分页查询用户
     */
    Page<UmsAdmin> list(String keyword, Integer pageSize, Integer pageNum);

    /**
     * 获取指定用户的可访问资源
     */
    List<UmsResource> getResourceList(Long adminId);

    /**
     * 获取用户信息
     */
    UserDetails loadUserByUsername(String username);

    /**
     * 获取缓存服务
     */
    UmsAdminCacheService getCacheService();
}
