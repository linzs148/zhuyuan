package com.nju.architecture.zhuyuan.modules.ums.dto.result;

import lombok.Data;

@Data
public class MessageRecordRespDTO {

    private String message;

    private Long senderId;

    private Long receiverId;

    private Long timestamp;

    private int topicId;

}
