package com.kyss.community.service.serviceimpl;

import com.kyss.community.enums.CommentTypeEnum;
import com.kyss.community.generator.dao.CommentMapper;
import com.kyss.community.generator.model.Comment;
import com.kyss.community.mapper.CommentMapperExt;
import com.kyss.community.mapper.QuestionMapperExt;
import com.kyss.community.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    QuestionMapperExt questionMapperExt;

    @Autowired
    CommentMapperExt commentMapperExt;

    @Override
    @Transactional
    public int insert(Comment comment) {

        int col = commentMapper.insert(comment);
        if (col == 1) {
            incCommentCount(comment.getParentId(), comment.getType());
        }
        return col;
    }

    @Override
    public int incCommentCount(Long parentId, Integer type) {
        int col = 0;
        if (CommentTypeEnum.COMMENT_ON_QUESTION.getType().equals(type)) {
            col = questionMapperExt.incCommentCount(parentId);
        } else {
            col = commentMapperExt.incCommentCount(parentId);
        }
        return col;
    }
}
