package com.kyss.community.interceptor;

import com.kyss.community.generator.dao.UserMapper;
import com.kyss.community.generator.model.User;
import com.kyss.community.generator.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @ClassName LoginInteceptor
 * @Description TODO
 * @Author davidt
 * @Date 6/12/2020 5:18 PM
 * @Version 1.0
 **/

@Configuration
public class LoginInteceptor implements HandlerInterceptor {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    UserExample example = new UserExample();
                    example.createCriteria().andTokenEqualTo(cookie.getValue());
                    List<User> user = userMapper.selectByExample(example);
                    if (user.size() != 0) {
                        request.getSession().setAttribute("user", user.get(0));
                    }
                    break;
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                Exception ex) throws Exception {

    }
}
