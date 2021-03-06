package com.kyss.community.controller;

import com.kyss.community.dto.QuestionDTO;
import com.kyss.community.exception.CustomizeErrorCode;
import com.kyss.community.exception.CustomizeException;
import com.kyss.community.generator.model.Question;
import com.kyss.community.generator.model.User;
import com.kyss.community.service.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName PublishController
 * @Description TODO
 * @Author davidt
 * @Date 6/5/2020 3:18 PM
 * @Version 1.0
 **/

@Controller
public class PublishController {

    @Autowired
    private IQuestionService questionService;

    @GetMapping("/publish")
    public String publish(Model model) {
        Question question = new Question();
        model.addAttribute("question", question);
        return "publish";
    }

    @GetMapping("/publish/{id}")
    public String publish(@PathVariable("id") Long id, Model model) {
        QuestionDTO questionDTO = questionService.queryById(id);
        if (questionDTO == null) {
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        model.addAttribute("question", questionDTO);
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam(name = "title") String title,
            @RequestParam(name = "description", required = false) String description,
            @RequestParam(name = "tag", required = false) String tag,
            @RequestParam(name = "id", required = false) Long id,
            HttpServletRequest request,
            Model model
    ) {
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setTitle(title);
        questionDTO.setDescription(description);
        questionDTO.setTag(tag);
        model.addAttribute("question", questionDTO);

        if (title == null || title == "") {
            model.addAttribute("error", "Title cannot be null!");
            return "publish";
        }

        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            Question question = new Question();
            question.setTitle(title);
            question.setDescription(description);
            question.setTag(tag);
            question.setCreator(user.getId());
            question.setId(id);
            question.setViewCount(0);
            question.setLikeCount(0);
            question.setCommentCount(0);
            int influenceRows = questionService.insertOrUpdate(question);
            if (influenceRows == 0) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
        } else {
            model.addAttribute("error", "Please login first!");
            return "publish";
        }
        return "redirect:/";
    }


}
