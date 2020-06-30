package com.kyss.community.enums;

/**
 * @ClassName NotificationEnum
 * @Description TODO
 * @Author davidt
 * @Date 6/29/2020 2:08 PM
 * @Version 1.0
 **/
public enum NotificationTypeEnum {
    NOTIFY_ON_COMMENT(1, "comment on comment: "),
    NOTIFY_ON_QUESTION(2, "comment on question: ");

    private int type;
    private String name;

    NotificationTypeEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public static String getNameByType(int type) {
        for (NotificationTypeEnum value : NotificationTypeEnum.values()) {
            if (value.getType() == type) {
                return value.getName();
            }
        }
        return null;
    }
}
