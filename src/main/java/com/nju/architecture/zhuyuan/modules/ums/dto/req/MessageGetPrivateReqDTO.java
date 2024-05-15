package com.nju.architecture.zhuyuan.modules.ums.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class MessageGetPrivateReqDTO {

    @Schema(title = "用户1ID", required = true)
    Long userId1;

    @Schema(title = "用户2ID", required = true)
    Long userId2;

    @Schema(title = "页码", required = true)
    int current;

    @Schema(title = "页面大小", required = true)
    int pageSize;

}
