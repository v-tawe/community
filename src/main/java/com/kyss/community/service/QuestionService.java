package com.kyss.community.service;

import com.kyss.community.dto.QuestionDTO;
import com.kyss.community.modle.Question;

import java.awt.desktop.QuitEvent;

/**
 * @ClassName QuestionService
 * @Description TODO
 * @Author davidt
 * @Date 6/12/2020 6:39 PM
 * @Version 1.0
 **/

public interface QuestionService {
    QuestionDTO queryById(Long questionId);

    Integer createOrUpdate(Question question);
}
