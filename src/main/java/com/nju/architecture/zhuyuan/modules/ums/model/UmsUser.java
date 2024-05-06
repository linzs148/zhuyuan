package com.nju.architecture.zhuyuan.modules.ums.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author macro
 * @since 2024-05-06
 */
@Data
@TableName("ums_user")
@Schema(name = "UmsUser", description = "用户表")
public class UmsUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String username;

    private String password;

    private String icon;

    private Date createTime;

    private Date loginTime;

    private String phone;
}
