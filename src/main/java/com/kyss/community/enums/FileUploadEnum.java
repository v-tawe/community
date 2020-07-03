package com.kyss.community.enums;

/**
 * @ClassName FileUploadEnum
 * @Description TODO
 * @Author davidt
 * @Date 7/3/2020 3:54 PM
 * @Version 1.0
 **/
public enum  FileUploadEnum {
    UPLOAD_SUCCESS(1, "upload success"),
    UPLOAD_FILED(0, "upload failed");
    private Integer success;
    private String message;

    public Integer getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    FileUploadEnum(Integer success, String message) {
        this.success = success;
        this.message = message;
    }
}
