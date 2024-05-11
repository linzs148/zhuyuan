package com.nju.architecture.zhuyuan.modules.ums.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lwp
 * @since 2024-05-11
 */
@Data
@TableName("message_record")
@Schema(name = "MessageRecord", description = "消息表")
public class MessageRecord implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String message;

    private int senderId;

    private int receiverId;

    private Date timestamp;

}
