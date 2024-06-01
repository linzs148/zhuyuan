package com.nju.architecture.zhuyuan.modules.ums.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nju.architecture.zhuyuan.modules.ums.dto.req.MessageRecordReqDTO;
import com.nju.architecture.zhuyuan.modules.ums.mapper.ProfileMapper;
import com.nju.architecture.zhuyuan.modules.ums.mapper.TopicMapper;
import com.nju.architecture.zhuyuan.modules.ums.model.MessageRecord;
import com.nju.architecture.zhuyuan.modules.ums.model.MessageTopic;
import com.nju.architecture.zhuyuan.modules.ums.model.UserProfile;
import com.nju.architecture.zhuyuan.modules.ums.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl extends ServiceImpl<ProfileMapper, UserProfile> implements ProfileService {

    @Autowired
    private ProfileMapper profileMapper;

    public void storeMessage(MessageRecordReqDTO messageRecordReqDTO) {

    }

}
