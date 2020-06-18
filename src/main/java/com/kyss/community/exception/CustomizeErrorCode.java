package com.kyss.community.exception;

/**
 * @ClassName CustomizeErrorCode
 * @Description TODO
 * @Author davidt
 * @Date 6/17/2020 2:42 PM
 * @Version 1.0
 **/
public enum CustomizeErrorCode implements ICustomizeErrorCode {
    PAGE_NOT_FOUND(4004, "Page not found" ),
    SERVER_DEFAULT_ERROR(5001, "Server default error."),
    QUESTION_NOT_FOUND(2001, "Question is not exist."),
    USER_NOT_LOGIN(1001, "User not login, login first please."),
    COMMENT_NOT_FOUND(3001, "Comment is not exist."),
    COMMENT_NO_PARENT(3002, "The question/comment you comment is not exist."),
    COMMENT_TYPE_WRONG(3003, "The comment type is wrong.");

    private String message;
    private Integer code;

    CustomizeErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }
}
