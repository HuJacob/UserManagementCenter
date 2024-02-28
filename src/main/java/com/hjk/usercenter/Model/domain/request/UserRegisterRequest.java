package com.hjk.usercenter.Model.domain.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author hjk
 */
@Data
public class UserRegisterRequest implements Serializable {

    private static final long serialVersionUID = -2326370109544187656L;

    private String userAccount;

    private String userPassword;

    private String checkPassword;
}
