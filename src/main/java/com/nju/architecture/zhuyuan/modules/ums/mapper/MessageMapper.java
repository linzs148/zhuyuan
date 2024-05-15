package com.nju.architecture.zhuyuan.modules.ums.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nju.architecture.zhuyuan.modules.ums.dto.result.MessageListRespDTO;
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
     * 根据两用户的id返回他们私聊的消息
     */
    Page<MessageRecordRespDTO> selectMessagesById(@Param("userId1") Long userId1, @Param("userId2") Long userId2, IPage<MessageRecordRespDTO> page);

    /**
     * 根据群聊ID返回群聊消息
     */
    Page<MessageRecordRespDTO> selectMessagesByTopicId(@Param("topicId") int topicId, IPage<MessageRecordRespDTO> page);

    /**
     * 根据用户ID返回消息列表
     */
    List<MessageRecord> selectMessagesById(@Param("userId") Long userId);

}
