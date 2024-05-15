package com.nju.architecture.zhuyuan.modules.ums.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nju.architecture.zhuyuan.modules.ums.dto.result.MessageRecordRespDTO;
import com.nju.architecture.zhuyuan.modules.ums.dto.result.MessageTopicRespDTO;
import com.nju.architecture.zhuyuan.modules.ums.model.MessageTopic;
import org.apache.ibatis.annotations.Param;

public interface TopicMapper extends BaseMapper<MessageTopic> {

    /**
     * 根据群聊ID返回群聊信息
     */
    Page<MessageTopicRespDTO> selectTopicByTopicId(@Param("topicId") int topicId, IPage<MessageTopicRespDTO> page);

}
