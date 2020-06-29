package com.kyss.community.controller;

import com.github.pagehelper.PageInfo;
import com.kyss.community.dto.NotificationDTO;
import com.kyss.community.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName NotificationController
 * @Description TODO
 * @Author davidt
 * @Date 6/29/2020 4:26 PM
 * @Version 1.0
 **/

@Controller
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @RequestMapping("/notification")
    public String notification(HttpServletRequest request, Model model, @RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "5") int pageSize) {

        // query questions list
        PageInfo<NotificationDTO> notificationDTOPageInfo = notificationService.listAll(pageNo, pageSize);
        model.addAttribute("notifications", notificationDTOPageInfo);
        return "notification";
    }
}
