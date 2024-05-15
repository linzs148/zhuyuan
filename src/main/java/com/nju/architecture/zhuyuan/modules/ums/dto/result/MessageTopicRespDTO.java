package com.nju.architecture.zhuyuan.modules.ums.dto.result;

import lombok.Data;

import java.util.Date;

@Data
public class MessageTopicRespDTO {

    private int designerId;

    private int customerId;

    private int managerId;

    private String theme;

    private Date timestamp;

}
