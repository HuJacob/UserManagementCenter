package com.hjk.usercenter.Model.domain.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 登录请求实体类
 * @author hjk
 */
@Data
public class UserLoginRequest implements Serializable {
    private static final long serialVersionUID = -2326370109544187656L;
    private String userAccount;
    private String userPassword;
}
