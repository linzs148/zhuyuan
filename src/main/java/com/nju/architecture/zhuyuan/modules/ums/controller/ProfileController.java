package com.nju.architecture.zhuyuan.modules.ums.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nju.architecture.zhuyuan.common.api.CommonResult;
import com.nju.architecture.zhuyuan.modules.ums.dto.req.MessageGetPrivateReqDTO;
import com.nju.architecture.zhuyuan.modules.ums.dto.req.ProfileGetReqDTO;
import com.nju.architecture.zhuyuan.modules.ums.dto.req.ProfileUploadReqDTO;
import com.nju.architecture.zhuyuan.modules.ums.dto.result.MessageRecordRespDTO;
import com.nju.architecture.zhuyuan.modules.ums.dto.result.UserProfileRespDTO;
import com.nju.architecture.zhuyuan.modules.ums.mapper.ProfileMapper;
import com.nju.architecture.zhuyuan.modules.ums.model.UserProfile;
import com.nju.architecture.zhuyuan.modules.ums.service.ProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author lwp
 * @since 5/24
 */
@Tag(name = "ProfileController")
@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private ProfileMapper profileMapper;

    /**
     * 获取个人主页
     *
     * @param profileGetReqDTO 获取个人主页DTO
     * @return 个人主页
     */
    @Operation(summary = "获取个人主页信息")
    @ResponseBody
    @GetMapping(value = "/getPersonalProfile")
    public CommonResult<UserProfileRespDTO> getPersonalProfile(@RequestBody ProfileGetReqDTO profileGetReqDTO) {
        UserProfile userProfile = profileMapper.selectById(profileGetReqDTO.getProfileId());
        UserProfileRespDTO userProfileRespDTO = new UserProfileRespDTO();
        BeanUtils.copyProperties(userProfile, userProfileRespDTO);
        return CommonResult.success(userProfileRespDTO);
    }

    /**
     * 上传个人主页设置
     *
     * @param profileUploadReqDTO 上传个人主页DTO
     *
     */
//    @Operation(summary = "上传个人主页信息")
//    @ResponseBody
//    @PostMapping(value = "/uploadPersonalProfile")
//    public CommonResult<Void> uploadPersonalProfile(@RequestBody ProfileUploadReqDTO profileUploadReqDTO) {
//        UserProfile userProfile = profileMapper.selectById(profileGetReqDTO.getProfileId());
//        UserProfileRespDTO userProfileRespDTO = new UserProfileRespDTO();
//        BeanUtils.copyProperties(userProfile, userProfileRespDTO);
//        return CommonResult.success();
//    }


}
