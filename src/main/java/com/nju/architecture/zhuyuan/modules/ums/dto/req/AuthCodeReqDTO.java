package com.nju.architecture.zhuyuan.modules.ums.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AuthCodeReqDTO {

    @Schema(title = "手机号码", required = true)
    String phone;

}
