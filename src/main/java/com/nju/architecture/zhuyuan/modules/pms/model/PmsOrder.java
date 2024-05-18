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
@TableName("pms_order")
@Schema(name = "PmsOrder", description = "")
public class PmsOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long pid;

    private String description1;

    private Integer rate1;

    private String description2;

    private Integer rate2;

    private String description3;

    private Integer rate3;

    private String cerator;

    private String designer;

    private String description;

    private Integer state;

    private Long cost;
}
