package com.kyss.community.dto;

import com.kyss.community.modle.User;
import lombok.Data;

/**
 * @ClassName QuestionDTO
 * @Description TODO
 * @Author davidt
 * @Date 6/11/2020 3:16 PM
 * @Version 1.0
 **/

@Data
public class QuestionDTO {
    private Long id;
    private String title;
    private String description;
    private String tag;
    private Integer commentCount;
    private Integer viewCount;
    private Long gmtCreate;
    private User user;
}
