package com.kyss.community.service.serviceimpl;

import com.kyss.community.dto.QuestionDTO;
import com.kyss.community.generator.dao.QuestionMapper;
import com.kyss.community.generator.model.Question;
import com.kyss.community.generator.model.QuestionExample;
import com.kyss.community.service.QuestionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
        QuestionExample example = new QuestionExample();
        Question question = questionMapper.selectByPrimaryKey(questionId);
        if (question == null) {
            return null;
        }
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);
        return questionDTO;
    }

    @Override
    public Integer createOrUpdate(Question question) {
        Question selectQuestion = questionMapper.selectByPrimaryKey(question.getId());
        Integer col = 0;
        if (selectQuestion == null) {
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            col = questionMapper.insertSelective(question);
        } else {
            question.setId(selectQuestion.getId());
            question.setGmtModified(System.currentTimeMillis());
            col = questionMapper.updateByPrimaryKey(question);
        }
        return col;
    }
}
