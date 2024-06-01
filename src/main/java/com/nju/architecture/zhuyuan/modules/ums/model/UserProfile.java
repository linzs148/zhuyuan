package com.nju.architecture.zhuyuan.modules.ums.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigInteger;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;
import java.sql.Timestamp;
import java.sql.Time;
import java.util.Objects;
import java.util.List;

/**
 * @author lwp
 * @since 2024-05-24
 */
@Data
@TableName("user_profile")
@Schema(name = "UserProfile", description = "个人中心")
public class UserProfile {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

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

