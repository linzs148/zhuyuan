package com.nju.architecture.zhuyuan.modules.ums.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nju.architecture.zhuyuan.modules.ums.dto.req.CreateTopicReqDTO;
import com.nju.architecture.zhuyuan.modules.ums.model.MessageTopic;

public interface TopicService extends IService<MessageTopic> {

    void storeTopic(CreateTopicReqDTO createTopicReqDTO);

}
