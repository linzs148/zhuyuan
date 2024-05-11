package com.nju.architecture.zhuyuan.modules.ums.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class MessageGetReqDTO {

    @Schema(title = "用户ID", required = true)
    Long userId;

    @Schema(title = "页码", required = true)
    int current;

    @Schema(title = "页面大小", required = true)
    int pageSize;

}
