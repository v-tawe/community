package com.kyss.community.dto;

import lombok.Data;

/**
 * @ClassName CommentDTO
 * @Description TODO
 * @Author davidt
 * @Date 6/17/2020 5:38 PM
 * @Version 1.0
 **/

@Data
public class CommentDTO {
    private Long id;
    private Long parentId;
    private Integer type;
    private String content;
    private Long commenter;
    private Long gmtCreate;
    private Long gmtModified;
    private Long likeCount;
}
