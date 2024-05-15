package com.nju.architecture.zhuyuan.modules.ums.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class MessageListGetReqDTO {

    @Schema(title = "用户ID", required = true)
    Long userId;

}
