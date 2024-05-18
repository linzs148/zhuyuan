package com.nju.architecture.zhuyuan.modules.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nju.architecture.zhuyuan.modules.pms.dto.PmsOrderDTO;
import com.nju.architecture.zhuyuan.modules.pms.model.PmsOrder;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author macro
 * @since 2024-05-18
 */
public interface PmsOrderService extends IService<PmsOrder> {

    void create(PmsOrderDTO pmsOrderDTO);

    void update(PmsOrderDTO pmsOrderDTO);

    PmsOrderDTO get(Long id);

}
