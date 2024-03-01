package com.hjk.usercenter.common;

import lombok.Data;

import java.io.Serializable;
import java.security.PrivilegedExceptionAction;

/**
 * 通用返回类
 * @param <T>
 * @author hjk
 */
@Data
public class BaseResponse<T> implements Serializable {
    private int code;

    private T data;

    private String message;

    public BaseResponse(int code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public BaseResponse(int code, T data){
        this(code, data, "");
    }
}
