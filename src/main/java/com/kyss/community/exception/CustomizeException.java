package com.kyss.community.exception;

/**
 * @ClassName CustomizeException
 * @Description TODO
 * @Author davidt
 * @Date 6/17/2020 11:13 AM
 * @Version 1.0
 **/
public class CustomizeException extends RuntimeException{
    private String message;

    public CustomizeException(String message) {
        this.message = message;
    }

    public CustomizeException(ICustomizeErrorCode errorCode) {
        this.message = errorCode.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
