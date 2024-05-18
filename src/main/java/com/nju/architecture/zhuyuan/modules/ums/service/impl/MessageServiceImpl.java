package com.nju.architecture.zhuyuan.modules.ums.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nju.architecture.zhuyuan.modules.ums.dto.req.MessageRecordReqDTO;
import com.nju.architecture.zhuyuan.modules.ums.dto.result.MessageListRespDTO;
import com.nju.architecture.zhuyuan.modules.ums.mapper.MessageMapper;
import com.nju.architecture.zhuyuan.modules.ums.mapper.TopicMapper;
import com.nju.architecture.zhuyuan.modules.ums.model.MessageRecord;
import com.nju.architecture.zhuyuan.modules.ums.model.MessageTopic;
import com.nju.architecture.zhuyuan.modules.ums.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lwp
 * @since 2024-05-11
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, MessageRecord> implements MessageService {

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private TopicMapper topicMapper;

    public void storeMessage(MessageRecordReqDTO messageRecordReqDTO) {
        MessageRecord messageRecord = new MessageRecord();
        BeanUtil.copyProperties(messageRecordReqDTO, messageRecord);
        save(messageRecord);
    }

    @Override
    public List<MessageListRespDTO> getMessageListById(Long userId) {
        List<MessageListRespDTO> messageListRespDTOS = new ArrayList<>();
        List<MessageRecord> messageRecords = messageMapper.selectMessagesById(userId);
        for (MessageRecord messageRecord : messageRecords) {
            if (messageRecord.getTopicId() != -1) {
                MessageListRespDTO messageListRespDTO = new MessageListRespDTO();
                messageListRespDTO.setTopicId(messageRecord.getTopicId());
                MessageTopic messageTopic = topicMapper.selectById(messageRecord.getTopicId());
                messageListRespDTO.setTheme(messageTopic.getTheme());
                messageListRespDTOS.add(messageListRespDTO);
            }
        }
        return null;
    }

}
