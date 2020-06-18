package com.kyss.community.exception;

/**
 * @ClassName CustomizeException
 * @Description TODO
 * @Author davidt
 * @Date 6/17/2020 11:13 AM
 * @Version 1.0
 **/
public class CustomizeException extends RuntimeException {
    private Integer code;
    private String message;

    public CustomizeException(ICustomizeErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }
}
