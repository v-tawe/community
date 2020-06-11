package com.kyss.community.controller;

import com.kyss.community.dto.QuestionDTO;
import com.kyss.community.mapper.QuestionMapper;
import com.kyss.community.mapper.UserMapper;
import com.kyss.community.modle.Question;
import com.kyss.community.modle.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
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
    QuestionMapper questionMapper;

    @Autowired
    UserMapper userMapper;

    @GetMapping("/publish")
    public String publish(Model model) {
        model.addAttribute("question", new QuestionDTO());
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam(name = "title") String title,
            @RequestParam(name = "description") String description,
            @RequestParam(name = "tag") String tag,
            HttpServletRequest request,
            Model model
    ) {
        String token = "";
        User user = null;

        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setTitle(title);
        questionDTO.setDescription(description);
        questionDTO.setTag(tag);
        model.addAttribute("question", questionDTO);

        if (title == null || title == "") {
            model.addAttribute("error", "Title cannot be null!");
            return "publish";
        }

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    token = cookie.getValue();
                    break;
                }
            }
            if (!token.isEmpty()) {
                user = userMapper.findByToken(token);
            }
        }
        if (user != null) {
            Question question = new Question();
            question.setTitle(title);
            question.setDescription(description);
            question.setTag(tag);
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            question.setCreator(user.getId());
            questionMapper.create(question);
        } else {
//            request.getSession().setAttribute("error", "Login First Please!");
            model.addAttribute("error", "Please login first!");
            return "publish";
        }
        return "redirect:/";
    }
}
