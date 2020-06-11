package com.kyss.community.modle;

import lombok.Data;

/**
 * @ClassName Question
 * @Description TODO
 * @Author davidt
 * @Date 6/5/2020 4:16 PM
 * @Version 1.0
 **/
@Data
public class Question {

    private Long id;
    private String title;
    private String description;
    private Long gmtCreate;
    private Long gmtModified;
    private Long creator;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private String tag;
}
