package com.kyss.community.service.serviceimpl;

import com.kyss.community.dto.QuestionDTO;
import com.kyss.community.mapper.QuestionMapper;
import com.kyss.community.modle.Question;
import com.kyss.community.service.QuestionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Queue;

/**
 * @ClassName QuestionServiceImpl
 * @Description TODO
 * @Author davidt
 * @Date 6/12/2020 6:39 PM
 * @Version 1.0
 **/
@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public QuestionDTO queryById(Long questionId) {
        Question question = questionMapper.selectById(questionId);
        if (question == null) {
            return null;
        }
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);
        return questionDTO;
    }

    @Override
    public Integer createOrUpdate(Question question) {
        QuestionDTO questionDTO = queryById(question.getId());
        Integer col = 0;
        if (questionDTO == null) {
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            col = questionMapper.create(question);
        } else {
            BeanUtils.copyProperties(question, questionDTO);
            question.setGmtModified(System.currentTimeMillis());
            col = questionMapper.update(questionDTO);
        }
        return col;
    }
}
