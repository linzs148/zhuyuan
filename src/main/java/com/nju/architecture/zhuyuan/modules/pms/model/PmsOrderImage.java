package com.nju.architecture.zhuyuan.modules.pms.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author macro
 * @since 2024-05-18
 */
@Getter
@Setter
@TableName("pms_order_image")
@Schema(name = "PmsOrderImage", description = "")
public class PmsOrderImage implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long oid;

    private String image;

    private Integer type;
}
