package com.nju.architecture.zhuyuan.modules.ums.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ProfileGetReqDTO {

    @Schema(title = "个人主页ID", required = true)
    int profileId;

}
