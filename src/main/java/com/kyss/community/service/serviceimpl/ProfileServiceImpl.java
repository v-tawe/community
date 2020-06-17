package com.kyss.community.service.serviceimpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kyss.community.dto.QuestionDTO;
import com.kyss.community.generator.dao.QuestionMapper;
import com.kyss.community.generator.model.Question;
import com.kyss.community.generator.model.QuestionExample;
import com.kyss.community.generator.model.User;
import com.kyss.community.service.ProfileService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName ProfileServiceImpl
 * @Description TODO
 * @Author davidt
 * @Date 6/12/2020 3:08 PM
 * @Version 1.0
 **/

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    QuestionMapper questionMapper;

    @Override
    public PageInfo<QuestionDTO> listAll(Long userId, int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andIdEqualTo(userId);
        PageInfo<Question> questionList = new PageInfo<> (questionMapper.selectByExample(questionExample));
        PageInfo<QuestionDTO> questionDTOList = new PageInfo<>(new Page<QuestionDTO>());
        BeanUtils.copyProperties(questionList, questionDTOList);
        return questionDTOList;
    }

    @Override
    public PageInfo<QuestionDTO> listAllOrderByViews(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize, "view_count desc");
        PageInfo<Question> questionList = new PageInfo<> (questionMapper.selectByExample(new QuestionExample()));
        PageInfo<QuestionDTO> questionDTOList = new PageInfo<>(new Page<QuestionDTO>());
        BeanUtils.copyProperties(questionList, questionDTOList);
        return questionDTOList;
    }
}
