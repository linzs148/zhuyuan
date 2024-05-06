package com.nju.architecture.zhuyuan.modules.ums.service.impl;

import com.nju.architecture.zhuyuan.common.service.RedisService;
import com.nju.architecture.zhuyuan.modules.ums.model.UmsAdmin;
import com.nju.architecture.zhuyuan.modules.ums.model.UmsResource;
import com.nju.architecture.zhuyuan.modules.ums.service.UmsAdminCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 后台用户缓存管理Service实现类
 * Created by macro on 2020/3/13.
 */
@Service
public class UmsAdminCacheServiceImpl implements UmsAdminCacheService {
    @Autowired
    private RedisService redisService;
    @Value("${redis.database}")
    private String REDIS_DATABASE;
    @Value("${redis.expire.common}")
    private Long REDIS_EXPIRE;
    @Value("${redis.key.admin}")
    private String REDIS_KEY_ADMIN;
    @Value("${redis.key.resourceList}")
    private String REDIS_KEY_RESOURCE_LIST;

    @Override
    public UmsAdmin getAdmin(String username) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" + username;
        return (UmsAdmin) redisService.get(key);
    }

    @Override
    public void setAdmin(UmsAdmin admin) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" + admin.getUsername();
        redisService.set(key, admin, REDIS_EXPIRE);
    }

    @Override
    public List<UmsResource> getResourceList(Long adminId) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + ":" + adminId;
        return (List<UmsResource>) redisService.get(key);
    }

    @Override
    public void setResourceList(Long adminId, List<UmsResource> resourceList) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + ":" + adminId;
        redisService.set(key, resourceList, REDIS_EXPIRE);
    }
}
