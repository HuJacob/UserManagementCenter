package com.hjk.usercenter.Model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
    private String user_name;

    /**
     * 账号
     */
    private String user_account;

    /**
     * ⽤户头像
     */
    private String avatar_url;

    /**
     * 性别
     */
    private Byte gender;

    /**
     * 密码
     */
    private String user_password;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 状态 0-正常
     */
    private Integer user_status;

    /**
     * 电话
     */
    private String phone;

    /**
     * 创建时间
     */
    private Date gmt_create;

    /**
     * 更新时间
     */
    private Date gmt_modified;

    /**
     * 是否删除
     */
    private Byte is_delete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}