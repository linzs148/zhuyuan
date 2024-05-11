package com.nju.architecture.zhuyuan.modules.ums.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * 用户发消息参数
 * Created by lwp on 2024/5/11
 */
@Data
public class MessageRecordReqDTO {

    @Schema(title = "消息内容", required = true)
    private String message;

    @Schema(title = "发送方id", required = true)
    private Long senderId;

    @Schema(title = "接收方id", required = true)
    private Long receiverId;

    @Schema(title = "发送时间", required = true)
    private Date timestamp;
}
