package com.nju.architecture.zhuyuan.modules.ums.dto.result;

import lombok.Data;

@Data
public class UserProfileRespDTO {

    private Long userId;

    private int userType;

    private String userName;

    private byte[] profilePhoto;

    private byte[] backgroundPicture;

    private String ipPlace;

    private String userTag;

    private int followerNumber;

    private String followerList;

    private int followingNumber;

    private String followingList;

    private String watchHistory;

    private String collection;

    private String productList;

}
