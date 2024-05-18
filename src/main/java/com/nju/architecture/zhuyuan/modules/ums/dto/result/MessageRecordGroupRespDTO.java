package com.nju.architecture.zhuyuan.modules.ums.dto.result;

import lombok.Data;

import java.util.List;

@Data
public class MessageRecordGroupRespDTO {

    int pageNo;
    int pageSize;
    private List<MessageRecordRespDTO> messageRecordList;

}
