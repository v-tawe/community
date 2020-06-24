package com.kyss.community.controller;

import com.kyss.community.dto.CommentDTO;
import com.kyss.community.dto.ResultDTO;
import com.kyss.community.enums.CommentTypeEnum;
import com.kyss.community.exception.CustomizeErrorCode;
import com.kyss.community.exception.CustomizeException;
import com.kyss.community.generator.dao.CommentMapper;
import com.kyss.community.generator.dao.QuestionMapper;
import com.kyss.community.generator.model.Comment;
import com.kyss.community.generator.model.Question;
import com.kyss.community.generator.model.User;
import com.kyss.community.service.ICommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName CommentController
 * @Description TODO
 * @Author davidt
 * @Date 6/17/2020 5:28 PM
 * @Version 1.0
 **/

@RestController
public class CommentController {

    @Autowired
    private ICommentService commentService;

    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    CommentMapper commentMapper;


    @PostMapping("/comment")
    public ResultDTO comment(@RequestBody CommentDTO commentDTO, HttpServletRequest request) {
        User user = (User)request.getSession().getAttribute("user");
        if (user == null) {
            throw new CustomizeException(CustomizeErrorCode.USER_NOT_LOGIN);
        }

        Comment comment = new Comment();
        BeanUtils.copyProperties(commentDTO, comment);
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(comment.getGmtCreate());
        comment.setCommenter(user.getId());
        comment.setLikeCount(0L);

        if (comment.getParentId() == null || comment.getParentId() == 0) {
            throw new CustomizeException(CustomizeErrorCode.COMMENT_NO_PARENT);
        }
        if (comment.getType() == null || !CommentTypeEnum.IS_TYPE_EXIST(comment.getType())) {
            throw new CustomizeException(CustomizeErrorCode.COMMENT_TYPE_WRONG);
        }
        if (comment.getType().equals(CommentTypeEnum.COMMENT_ON_QUESTION.getType())) {
            // comment on question
            Question question = questionMapper.selectByPrimaryKey(comment.getParentId());
            if (question == null) {
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NO_PARENT);
            }
        } else {
            // comment on comment
            Comment parComment = commentMapper.selectByPrimaryKey(comment.getParentId());
            if (parComment == null) {
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NO_PARENT);
            }
        }

        int col = commentService.insert(comment);
        if (col == 1) {
            return ResultDTO.successCode();
        }
        return ResultDTO.errorCode();
    }
}
