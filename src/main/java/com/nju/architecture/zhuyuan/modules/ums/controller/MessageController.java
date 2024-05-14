package com.nju.architecture.zhuyuan.modules.ums.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nju.architecture.zhuyuan.common.api.CommonResult;
import com.nju.architecture.zhuyuan.modules.ums.dto.req.CreateTopicReqDTO;
import com.nju.architecture.zhuyuan.modules.ums.dto.req.MessageGetReqDTO;
import com.nju.architecture.zhuyuan.modules.ums.dto.req.MessageRecordReqDTO;
import com.nju.architecture.zhuyuan.modules.ums.dto.result.MessageRecordGroupRespDTO;
import com.nju.architecture.zhuyuan.modules.ums.dto.result.MessageRecordRespDTO;
import com.nju.architecture.zhuyuan.modules.ums.mapper.MessageMapper;
import com.nju.architecture.zhuyuan.modules.ums.service.MessageService;
import com.nju.architecture.zhuyuan.modules.ums.service.TopicService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
     * 获取信息
     * @param messageGetReqDTO 获取DTO
     * @return 分页信息
     */
    @Operation(summary = "获取信息")
    @ResponseBody
    @GetMapping(value = "/getMessagePage")
    public CommonResult<Page<MessageRecordRespDTO>> getMessagePage(@RequestBody MessageGetReqDTO messageGetReqDTO) {
        IPage<MessageRecordRespDTO> page = new Page<>(messageGetReqDTO.getCurrent(), messageGetReqDTO.getPageSize());
        return CommonResult.success(messageMapper.selectMessagesById(messageGetReqDTO.getUserId(), page));
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

}
