package com.nju.architecture.zhuyuan.modules.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nju.architecture.zhuyuan.modules.pms.model.PmsProductImage;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author macro
 * @since 2024-05-11
 */
public interface PmsProductImageService extends IService<PmsProductImage> {

    String getOneImage(Long pid);

    List<String> listImages(Long pid);

}
