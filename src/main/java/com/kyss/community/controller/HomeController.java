package com.kyss.community.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kyss.community.dto.QuestionDTO;
import com.kyss.community.mapper.QuestionMapper;
import com.kyss.community.modle.Question;
import com.kyss.community.modle.User;
import com.kyss.community.mapper.UserMapper;
import com.kyss.community.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName HomeController
 * @Description TODO
 * @Author davidt
 * @Date 6/3/2020 5:53 PM
 * @Version 1.0
 **/

@Controller
public class HomeController {

    @Autowired
    UserMapper userMapper;

    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    HomeService homeService;

    @RequestMapping("/")
    public String index(HttpServletRequest request, Model model, @RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "2") int pageSize) {

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    User user = userMapper.findByToken(cookie.getValue());
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }

        // query questions list
        PageInfo<QuestionDTO> questionDTOList = homeService.listAll(pageNo, pageSize);
        model.addAttribute("questions", questionDTOList);
        return "index";
    }
}
