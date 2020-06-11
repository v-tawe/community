package com.kyss.community.service.serviceimpl;

import com.kyss.community.dto.QuestionDTO;
import com.kyss.community.mapper.QuestionMapper;
import com.kyss.community.mapper.UserMapper;
import com.kyss.community.modle.Question;
import com.kyss.community.modle.User;
import com.kyss.community.service.HomeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName HomeServiceImpl
 * @Description TODO
 * @Author davidt
 * @Date 6/11/2020 3:43 PM
 * @Version 1.0
 **/

@Service
public class HomeServiceImpl implements HomeService {

    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public List<QuestionDTO> listAll() {
        List<Question> questionList = questionMapper.selectAll();
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questionList) {
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            User user = userMapper.findById(question.getCreator());
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }
}
