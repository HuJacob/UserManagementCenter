package com.hjk.usercenter.Model;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用户表
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * ⽤户昵称
     */
    private String userName;

    /**
     * 账号
     */
    private String userAccount;

    /**
     * ⽤户头像
     */
    private String avatarUrl;

    /**
     * 性别
     */
    private Byte gender;

    /**
     * 密码
     */
    private String userPassword;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 状态 0-正常
     */
    private Integer userStatus;

    /**
     * 电话
     */
    private String phone;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 更新时间
     */
    private Date gmtModified;

    /**
     * 是否删除
     */
    @TableLogic
    private Byte isDelete;

    /**
     * 用户权限，默认-0，管理员-1
     */
    private Integer userRole;

    /**
     * 邀请码
     */
    private String planetCode;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}