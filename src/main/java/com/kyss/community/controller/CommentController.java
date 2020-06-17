package com.kyss.community.controller;

import com.kyss.community.dto.CommentDTO;
import com.kyss.community.generator.model.Comment;
import com.kyss.community.service.ICommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName CommentController
 * @Description TODO
 * @Author davidt
 * @Date 6/17/2020 5:28 PM
 * @Version 1.0
 **/

@Controller
//@RequestMapping("/question/{questionId}/comment/")
public class CommentController {

    @Autowired
    private ICommentService commentService;

//    @RequestMapping("{commentId}")
    @PostMapping("/comment")
    public String comment(@RequestBody CommentDTO commentDTO) {
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentDTO, comment);
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(comment.getGmtCreate());
        commentService.insert(comment);
        return null;
    }
}
