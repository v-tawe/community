package com.kyss.community.interceptor;

import com.kyss.community.enums.NotificationStatusEnum;
import com.kyss.community.enums.NotificationTypeEnum;
import com.kyss.community.generator.dao.NotificationMapper;
import com.kyss.community.generator.model.NotificationExample;
import com.kyss.community.generator.model.UserExample;
import org.h2.engine.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName NotificationInteceptor
 * @Description query notification nums before page load
 * @Author davidt
 * @Date 6/29/2020 5:53 PM
 * @Version 1.0
 **/

@Configuration
public class NotificationInterceptor implements HandlerInterceptor {

    @Autowired
    private NotificationMapper notificationMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria().andStatusEqualTo(NotificationStatusEnum.UNREAD.getStatus());
        Long count = notificationMapper.countByExample(notificationExample);
        request.getSession().setAttribute("notificationCounts", count);
        return true;
    }
}
