package com.kyss.community.controller;

import com.kyss.community.dto.QuestionDTO;
import com.kyss.community.exception.CustomizeErrorCode;
import com.kyss.community.exception.CustomizeException;
import com.kyss.community.generator.dao.QuestionMapper;
import com.kyss.community.generator.model.Question;
import com.kyss.community.service.QuestionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName QuestionController
 * @Description TODO
 * @Author davidt
 * @Date 6/12/2020 6:35 PM
 * @Version 1.0
 **/

@Controller
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("{questionId}")
    public String question(@PathVariable("questionId") Long questionId, Model model) {
        QuestionDTO questionDTO = questionService.queryById(questionId);
        if (questionDTO == null) {
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        questionService.incViewCount(questionId);
        // 返回前使 DTO 里 viewCount + 1
        questionDTO.setViewCount(questionDTO.getViewCount()+1);
        model.addAttribute("question", questionDTO);
        return "question";
    }
}
