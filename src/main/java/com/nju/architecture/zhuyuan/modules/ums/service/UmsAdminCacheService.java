package com.nju.architecture.zhuyuan.modules.ums.service;



import com.nju.architecture.zhuyuan.modules.ums.model.UmsAdmin;
import com.nju.architecture.zhuyuan.modules.ums.model.UmsResource;

import java.util.List;

/**
 * 后台用户缓存管理Service
 * Created by macro on 2020/3/13.
 */
public interface UmsAdminCacheService {
    /**
     * 获取缓存后台用户信息
     */
    UmsAdmin getAdmin(String username);

    /**
     * 设置缓存后台用户信息
     */
    void setAdmin(UmsAdmin admin);

    /**
     * 获取缓存后台用户资源列表
     */
    List<UmsResource> getResourceList(Long adminId);

    /**
     * 设置后台后台用户资源列表
     */
    void setResourceList(Long adminId, List<UmsResource> resourceList);
}
