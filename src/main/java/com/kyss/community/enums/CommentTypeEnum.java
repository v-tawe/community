package com.kyss.community.enums;

import com.kyss.community.generator.model.Comment;

/**
 * @ClassName CommentTypeEnum
 * @Description TODO
 * @Author davidt
 * @Date 6/18/2020 4:31 PM
 * @Version 1.0
 **/
public enum CommentTypeEnum {
    COMMENT_ON_QUESTION(1),
    COMMENT_ON_COMMENT(2);

    CommentTypeEnum(Integer type) {
        this.type = type;
    }

    private Integer type;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public static boolean IS_TYPE_EXIST(Integer type) {
        for (CommentTypeEnum value : CommentTypeEnum.values()) {
           if (value.getType().equals(type)) {
               return true;
           }
        }
        return false;
    }

}
