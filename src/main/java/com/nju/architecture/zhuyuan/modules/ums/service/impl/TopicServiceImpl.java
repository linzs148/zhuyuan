package com.nju.architecture.zhuyuan.modules.ums.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nju.architecture.zhuyuan.modules.ums.dto.req.CreateTopicReqDTO;
import com.nju.architecture.zhuyuan.modules.ums.mapper.MessageMapper;
import com.nju.architecture.zhuyuan.modules.ums.mapper.TopicMapper;
import com.nju.architecture.zhuyuan.modules.ums.model.MessageRecord;
import com.nju.architecture.zhuyuan.modules.ums.model.MessageTopic;
import com.nju.architecture.zhuyuan.modules.ums.service.MessageService;
import com.nju.architecture.zhuyuan.modules.ums.service.TopicService;
import org.springframework.stereotype.Service;

@Service
public class TopicServiceImpl extends ServiceImpl<TopicMapper, MessageTopic> implements TopicService {


    @Override
    public boolean storeTopic(CreateTopicReqDTO createTopicReqDTO) {
        MessageTopic messageTopic = new MessageTopic();
        BeanUtil.copyProperties(createTopicReqDTO, messageTopic);
        save(messageTopic);
        return true;
    }
}
