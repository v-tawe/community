package com.kyss.community.enums;

/**
 * @ClassName NotificaitonStatusEnum
 * @Description TODO
 * @Author davidt
 * @Date 6/29/2020 2:22 PM
 * @Version 1.0
 **/
public enum NotificationStatusEnum {
    READ(1), UNREAD(0);

    NotificationStatusEnum(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    private int status;

    public static Boolean isRead(int status) {
        return status == NotificationStatusEnum.READ.getStatus() ? true : false;
    }
}
