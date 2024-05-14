package com.nju.architecture.zhuyuan.modules.ums.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nju.architecture.zhuyuan.modules.ums.dto.req.CreateTopicReqDTO;
import com.nju.architecture.zhuyuan.modules.ums.dto.req.MessageRecordReqDTO;
import com.nju.architecture.zhuyuan.modules.ums.model.MessageRecord;
import com.nju.architecture.zhuyuan.modules.ums.model.MessageTopic;
import org.springframework.stereotype.Service;

public interface TopicService extends IService<MessageTopic> {

    boolean storeTopic(CreateTopicReqDTO createTopicReqDTO);

}
