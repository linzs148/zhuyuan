package com.nju.architecture.zhuyuan.modules.ums.dto.result;

import lombok.Data;

import java.util.List;

@Data
public class MessageRecordGroupRespDTO {

    private List<MessageRecordRespDTO> messageRecordList;

    int pageNo;

    int pageSize;

}
