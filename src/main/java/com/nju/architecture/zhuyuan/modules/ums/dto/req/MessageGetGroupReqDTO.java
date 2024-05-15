package com.nju.architecture.zhuyuan.modules.ums.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class MessageGetGroupReqDTO {

    @Schema(title = "群聊ID", required = true)
    int topicId;

    @Schema(title = "页码", required = true)
    int current;

    @Schema(title = "页面大小", required = true)
    int pageSize;

}
