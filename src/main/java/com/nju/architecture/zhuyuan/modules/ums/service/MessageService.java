package com.nju.architecture.zhuyuan.modules.ums.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nju.architecture.zhuyuan.modules.ums.dto.req.MessageRecordReqDTO;
import com.nju.architecture.zhuyuan.modules.ums.dto.result.MessageListRespDTO;
import com.nju.architecture.zhuyuan.modules.ums.model.MessageRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lwp
 * @since 2024-05-11
 */
public interface MessageService extends IService<MessageRecord> {

    void storeMessage(MessageRecordReqDTO messageRecordReqDTO);

    List<MessageListRespDTO> getMessageListById(@Param("userId") Long userId);

}
