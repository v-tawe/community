package com.kyss.community.controller;

import com.github.pagehelper.PageInfo;
import com.kyss.community.dto.NotificationDTO;
import com.kyss.community.dto.QuestionDTO;
import com.kyss.community.generator.model.Notification;
import com.kyss.community.generator.model.User;
import com.kyss.community.service.IProfileService;
import com.kyss.community.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName ProfileController
 * @Description TODO
 * @Author davidt
 * @Date 6/12/2020 3:00 PM
 * @Version 1.0
 **/

@Controller
@RequestMapping("profile")
public class ProfileController {

    @Autowired
    private IProfileService profileService;

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/{action}")
    public String profile(@PathVariable("action") String action, HttpServletRequest request, Model model,
                          @RequestParam(defaultValue = "1") int pageNo,
                          @RequestParam(defaultValue = "5") int pageSize) {

        Long userId = ((User)request.getSession().getAttribute("user")).getId();
        Long countAllQuestions = profileService.countAllQuestions();
        model.addAttribute("countAllQuestions", countAllQuestions);
        Long countMyQuestions = profileService.countMyQuestions(userId);
        model.addAttribute("countMyQuestions", countMyQuestions);

        if ("notifications".equals(action)) {
            model.addAttribute("section", "notifications");
            PageInfo<NotificationDTO> notificationDTOPageInfo = notificationService.listAll(pageNo, pageSize);
            model.addAttribute("notifications", notificationDTOPageInfo);
            return "notifications";

        } else if ("myQuestions".equals(action)) {
            model.addAttribute("section", "myQuestions");
            // query questions list
            PageInfo<QuestionDTO> questionDTOList = profileService.listMyQuesitons(userId, pageNo, pageSize);
            model.addAttribute("questions", questionDTOList);

        } else if ("hotQuestions".equals(action)) {
            model.addAttribute("section", "hotQuestions");
            // query questions list
            PageInfo<QuestionDTO> questionDTOList = profileService.listAllOrderByViews(pageNo, pageSize);
            model.addAttribute("questions", questionDTOList);
        }
        return "profile";
    }

    @RequestMapping("/notification/{id}")
    public String notificationRedirect(@PathVariable("id") Long id) {

        Notification notification = notificationService.selectByPrimaryKey(id);
        notificationService.read(id);
        return "redirect:/question/" + notification.getOuterId();
    }

}
