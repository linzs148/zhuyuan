package com.nju.architecture.zhuyuan.modules.ums.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
public class CreateTopicReqDTO {

    @Schema(title = "设计者ID", required = true)
    int designerId;

    @Schema(title = "客户ID", required = true)
    int customerId;

    @Schema(title = "产品经理ID", required = true)
    int managerId;

    @Schema(title = "主题", required = true)
    String theme;

    @Schema(title = "时间戳", required = true)
    Date timestamp;

}