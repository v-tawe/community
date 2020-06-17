package com.kyss.community.exception;

/**
 * @ClassName CustomizeErrorCode
 * @Description TODO
 * @Author davidt
 * @Date 6/17/2020 2:42 PM
 * @Version 1.0
 **/
public enum CustomizeErrorCode implements ICustomizeErrorCode {
    QUESTION_NOT_FOUND("Question you searched is 404."),
    SERVER_DEFAULT_ERROR("Server default error.");

    private String message;

    CustomizeErrorCode(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
