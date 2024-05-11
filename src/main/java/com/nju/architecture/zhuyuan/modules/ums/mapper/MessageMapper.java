package com.nju.architecture.zhuyuan.modules.ums.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nju.architecture.zhuyuan.modules.ums.dto.result.MessageRecordRespDTO;
import com.nju.architecture.zhuyuan.modules.ums.model.MessageRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lwp
 * @since 2024-05-11
 */
public interface MessageMapper extends BaseMapper<MessageRecord> {

    /**
     * 根据用户ID返回他发送或者收到的消息
     * @return
     */
    Page<MessageRecordRespDTO> selectMessagesById(@Param("userId") Long userId, IPage<MessageRecordRespDTO> page);

}
