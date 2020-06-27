package com.kyss.community.service;

import com.kyss.community.dto.CommentDTO;
import com.kyss.community.generator.model.Comment;

import java.util.List;

/**
 * @ClassName CommentService
 * @Description TODO
 * @Author davidt
 * @Date 6/17/2020 5:29 PM
 * @Version 1.0
 **/
public interface ICommentService {
    int insert(Comment comment);
    int incCommentCount(Long parentId, Integer type);

    List<CommentDTO> listByQuestionId(Long questionId);
}
