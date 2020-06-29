package com.kyss.community.service.serviceimpl;

import com.kyss.community.dto.CommentDTO;
import com.kyss.community.enums.CommentTypeEnum;
import com.kyss.community.generator.dao.CommentMapper;
import com.kyss.community.generator.dao.UserMapper;
import com.kyss.community.generator.model.Comment;
import com.kyss.community.generator.model.CommentExample;
import com.kyss.community.generator.model.User;
import com.kyss.community.generator.model.UserExample;
import com.kyss.community.mapper.CommentMapperExt;
import com.kyss.community.mapper.QuestionMapperExt;
import com.kyss.community.service.ICommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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
    UserMapper userMapper;

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

    @Override
    public List<CommentDTO> listByQuestionId(Long questionId) {
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria()
                .andParentIdEqualTo(questionId)
                .andTypeEqualTo(CommentTypeEnum.COMMENT_ON_QUESTION.getType());
        commentExample.setOrderByClause("gmt_create desc");

        List<Comment> comments = commentMapper.selectByExample(commentExample);
        if (comments.size() == 0) {
            return new ArrayList<>();
        }

        // 获取去重评论人
        Set<Long> commenters = comments.stream().map(comment -> comment.getCommenter()).collect(Collectors.toSet());
        List<Long> commenterList = new ArrayList<>(commenters);

        // 获取评论人并转换为 Map
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andIdIn(commenterList);
        List<User> users = userMapper.selectByExample(userExample);
        Map<Long, User> userMap = users.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));

        // 转换 Comment 为 CommentDTO
        List<CommentDTO> commentDTOs = comments.stream().map(comment -> {
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment, commentDTO);
            commentDTO.setUser(userMap.get(comment.getCommenter()));
            return commentDTO;
        }).collect(Collectors.toList());
        return commentDTOs;
    }
}
