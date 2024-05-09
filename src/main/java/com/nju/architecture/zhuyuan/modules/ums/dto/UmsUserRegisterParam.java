package com.nju.architecture.zhuyuan.modules.ums.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * 用户注册参数
 * Created by linzs on 2024/5、6.
 */
@Data
public class UmsUserRegisterParam {

    @NotEmpty
    @Length(min = 11, max = 11)
    @Digits(integer = 11, fraction = 0)
    @Schema(title = "手机号", required = true)
    private String phone;

    @NotEmpty
    @Schema(title = "密码", required = true)
    private String password;

    @Schema(title = "验证码")
    @Length(min = 6, max = 6)
    @Digits(integer = 6, fraction = 0)
    private String code;
}
