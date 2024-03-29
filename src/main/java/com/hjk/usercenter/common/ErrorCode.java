package com.hjk.usercenter.common;

/**
 * 错误码
 * @author hjk
 */

public enum ErrorCode {
    /**
     * success 成功
     * 40000 请求参数错误
     * 40001 请求参数为空
     * 40100 未登录
     * 40101 无权限
     */
    SUCCESS(0, "ok",""),
    PARAMS_ERROR(40000, "请求参数错误",""),
    NULL_ERROR(40001,"请求参数为空",""),
    NOT_LOGIN(40100,"未登录", ""),
    NO_AUTH(40101, "无权限", ""),
    SYSTEM_ERROR(50000, "系统内部权限", "");

    /**
     * 状态码
     */
    private final int code;
    /**
     * 状态码信息
     */
    private final String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }

    /**
     * 状态码描述
     */
    private final String description;

    ErrorCode(int code, String message, String description) {
        this.code = code;
        this.message = message;
        this.description = description;
    }
}
