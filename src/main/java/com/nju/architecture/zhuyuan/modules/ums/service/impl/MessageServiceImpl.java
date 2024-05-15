package com.nju.architecture.zhuyuan.modules.ums.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nju.architecture.zhuyuan.modules.ums.dto.req.MessageRecordReqDTO;
import com.nju.architecture.zhuyuan.modules.ums.mapper.MessageMapper;
import com.nju.architecture.zhuyuan.modules.ums.model.MessageRecord;
import com.nju.architecture.zhuyuan.modules.ums.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lwp
 * @since 2024-05-11
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, MessageRecord> implements MessageService {

    @Autowired
    private MessageMapper messageMapper;

    public boolean storeMessage(MessageRecordReqDTO messageRecordReqDTO) {
        MessageRecord messageRecord = new MessageRecord();
        BeanUtil.copyProperties(messageRecordReqDTO, messageRecord);
        save(messageRecord);
        return true;
    }

}
