package com.kyss.community.service.serviceimpl;

import com.kyss.community.dto.QuestionDTO;
import com.kyss.community.generator.dao.QuestionMapper;
import com.kyss.community.generator.dao.UserMapper;
import com.kyss.community.generator.model.Question;
import com.kyss.community.generator.model.QuestionExample;
import com.kyss.community.mapper.QuestionMapperExt;
import com.kyss.community.service.IQuestionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName QuestionServiceImpl
 * @Description TODO
 * @Author davidt
 * @Date 6/12/2020 6:39 PM
 * @Version 1.0
 **/
@Service
public class QuestionServiceImpl implements IQuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapperExt questionMapperExt;

    @Override
    public QuestionDTO queryById(Long questionId) {
        QuestionExample example = new QuestionExample();
        Question question = questionMapper.selectByPrimaryKey(questionId);
        if (question == null) {
            return null;
        }
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);
        // add user to questionDTO
        questionDTO.setUser(userMapper.selectByPrimaryKey(questionDTO.getCreator()));
        return questionDTO;
    }

    @Override
    public Integer insertOrUpdate(Question question) {
        Question selectQuestion = questionMapper.selectByPrimaryKey(question.getId());
        Integer col = 0;
        if (selectQuestion == null) {
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            col = questionMapper.insertSelective(question);
        } else {
            question.setId(selectQuestion.getId());
            question.setGmtCreate(selectQuestion.getGmtCreate());
            question.setGmtModified(System.currentTimeMillis());
            col = questionMapper.updateByPrimaryKeyWithBLOBs(question);
        }
        return col;
    }

    @Override
    public void incViewCount(Long questionId) {
        questionMapperExt.incViewCount(questionId);
    }

    @Override
    public List<QuestionDTO> selectRelatedQuestions(QuestionDTO questionDTO) {
        String tags = questionDTO.getTag().replace(",","|");
        List<Question> questions = questionMapperExt.selectRelatedQuestions(tags);
        List<QuestionDTO> questionDTOS = questions.stream().map(question -> {
            QuestionDTO questionDTOTemp = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTOTemp);
            return questionDTOTemp;
        }).collect(Collectors.toList());
        return questionDTOS;
    }
}
