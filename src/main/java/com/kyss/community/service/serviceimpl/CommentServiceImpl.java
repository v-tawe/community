package com.kyss.community.service.serviceimpl;

import com.kyss.community.controller.CommentController;
import com.kyss.community.generator.dao.CommentMapper;
import com.kyss.community.generator.model.Comment;
import com.kyss.community.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName CommentServiceImpl
 * @Description TODO
 * @Author davidt
 * @Date 6/17/2020 5:36 PM
 * @Version 1.0
 **/

@Service
public class CommentServiceImpl implements ICommentService {

    @Autowired
    CommentMapper commentMapper;

    @Override
    public int insert(Comment comment) {
        int col = commentMapper.insert(comment);
        return col;
    }
}
