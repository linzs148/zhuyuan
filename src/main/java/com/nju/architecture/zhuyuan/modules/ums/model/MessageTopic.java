package com.nju.architecture.zhuyuan.modules.ums.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
@TableName("message_topic")
@Schema(name = "MessageTopic", description = "群聊表")
public class MessageTopic {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private int designerId;

    private int customerId;

    private int managerId;

    private String theme;

    private Date timestamp;

}
