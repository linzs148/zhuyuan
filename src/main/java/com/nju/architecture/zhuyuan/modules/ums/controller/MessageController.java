package com.nju.architecture.zhuyuan.modules.ums.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nju.architecture.zhuyuan.common.api.CommonResult;
import com.nju.architecture.zhuyuan.modules.ums.dto.req.*;
import com.nju.architecture.zhuyuan.modules.ums.dto.result.MessageListRespDTO;
import com.nju.architecture.zhuyuan.modules.ums.dto.result.MessageRecordRespDTO;
import com.nju.architecture.zhuyuan.modules.ums.dto.result.MessageTopicRespDTO;
import com.nju.architecture.zhuyuan.modules.ums.mapper.MessageMapper;
import com.nju.architecture.zhuyuan.modules.ums.mapper.TopicMapper;
import com.nju.architecture.zhuyuan.modules.ums.service.MessageService;
import com.nju.architecture.zhuyuan.modules.ums.service.TopicService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lwp
 * @since 5/11
 */
@Tag(name = "MessageController")
@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private TopicService topicService;

    @Autowired
    private TopicMapper topicMapper;

    /**
     * 发送信息
     * @return 成功
     */
    @Operation(summary = "发送信息")
    @ResponseBody
    @PostMapping(value = "/sendMessage")
    public CommonResult<Void> sendMessage(@RequestBody MessageRecordReqDTO messageRecordParam) {
        messageService.storeMessage(messageRecordParam);
        return CommonResult.success(null);
    }

    /**
     * 获取私聊信息
     * @param messageGetPrivateReqDTO 获取DTO
     * @return 分页信息
     */
    @Operation(summary = "分页获取私聊信息")
    @ResponseBody
    @GetMapping(value = "/getPrivateMessagePage")
    public CommonResult<Page<MessageRecordRespDTO>> getPrivateMessagePage(@RequestBody MessageGetPrivateReqDTO messageGetPrivateReqDTO) {
        IPage<MessageRecordRespDTO> page = new Page<>(messageGetPrivateReqDTO.getCurrent(), messageGetPrivateReqDTO.getPageSize());
        return CommonResult.success(messageMapper.selectMessagesById(messageGetPrivateReqDTO.getUserId1(), messageGetPrivateReqDTO.getUserId2(), page));
    }

    /**
     * 获取群聊信息
     * @param messageGetGroupReqDTO 获取DTO
     * @return 分页信息
     */
    @Operation(summary = "分页获取群聊信息")
    @ResponseBody
    @GetMapping(value = "/getGroupMessagePage")
    public CommonResult<Page<MessageRecordRespDTO>> getGroupMessagePage(@RequestBody MessageGetGroupReqDTO messageGetGroupReqDTO) {
        IPage<MessageRecordRespDTO> page = new Page<>(messageGetGroupReqDTO.getCurrent(), messageGetGroupReqDTO.getPageSize());
        return CommonResult.success(messageMapper.selectMessagesByTopicId(messageGetGroupReqDTO.getTopicId(), page));
    }


    /**
     * 创建群聊
     * @param createTopicReqDTO
     * @return 成功
     */
    @Operation(summary = "创建群聊")
    @ResponseBody
    @PostMapping(value = "/createTopic")
    public CommonResult<Page<MessageRecordRespDTO>> createTopic(@RequestBody CreateTopicReqDTO createTopicReqDTO) {
        topicService.storeTopic(createTopicReqDTO);
        return CommonResult.success(null);
    }

    /**
     * 获取群聊
     */
    @Operation(summary = "分页获取群聊")
    @ResponseBody
    @GetMapping(value = "/getTopicPage")
    public CommonResult<Page<MessageTopicRespDTO>> getTopicPage(@RequestBody TopicGetReqDTO topicGetReqDTO) {
        IPage<MessageTopicRespDTO> page = new Page<>(topicGetReqDTO.getCurrent(), topicGetReqDTO.getPageSize());
        return CommonResult.success(topicMapper.selectTopicByTopicId(topicGetReqDTO.getTopicId(), page));
    }

//    /**
//     * 获取用户聊天列表
//     */
//    @Operation(summary = "获取用户聊天列表")
//    @ResponseBody
//    @GetMapping(value = "/getMessageList")
//    public CommonResult<List<MessageListRespDTO>> getMessageList(@RequestBody MessageListGetReqDTO messageListGetReqDTO) {
//        return CommonResult.success(messageService.getMessageListById(messageListGetReqDTO.getUserId()));
//    }

}
