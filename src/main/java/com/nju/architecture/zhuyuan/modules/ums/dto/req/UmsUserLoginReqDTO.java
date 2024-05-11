package com.nju.architecture.zhuyuan.modules.ums.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 用户登录参数
 * Created by linzs on 2024/5、6.
 */
@Data
public class UmsUserLoginReqDTO {

    @Schema(title = "手机号", required = true)
    private String phone;

    @Schema(title = "密码")
    private String password;

    @Schema(title = "验证码")
    private String code;
}
