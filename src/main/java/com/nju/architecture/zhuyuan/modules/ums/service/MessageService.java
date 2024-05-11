package com.nju.architecture.zhuyuan.modules.ums.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nju.architecture.zhuyuan.modules.ums.dto.req.MessageRecordReqDTO;
import com.nju.architecture.zhuyuan.modules.ums.model.MessageRecord;

/**
 * @author lwp
 * @since 2024-05-11
 */
public interface MessageService extends IService<MessageRecord> {

    boolean storeMessage(MessageRecordReqDTO messageRecordParam);

}
