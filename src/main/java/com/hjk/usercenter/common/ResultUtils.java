package com.hjk.usercenter.common;

/**
 * 返回工具类
 * @author hjk
 */
public class ResultUtils {
    /**
     * 成功
     * @param data
     * @param <T>
     * @return BaseResponse
     */
    public static <T> BaseResponse<T> success(T data){
        return new BaseResponse<>(0, data, "ok");
    }

    /**
     * 失败
     * @param errorCode
     * @return BaseResponse
     */
    public static BaseResponse error(ErrorCode errorCode){
        return new BaseResponse<>(errorCode);
    }

    /**
     * 失败
     * @param code
     * @param message
     * @param description
     * @return BaseResponse
     */
    public static BaseResponse error(int code, String message, String description) {
        return new BaseResponse<>(code, null, message, description);
    }

    public static BaseResponse error(ErrorCode errorCode, String description) {
        return new BaseResponse<>(errorCode.getCode(), errorCode.getMessage(), description);
    }

    public static BaseResponse error(ErrorCode errorCode,String message, String description) {
        return new BaseResponse<>(errorCode.getCode(), message, description);
    }
}
