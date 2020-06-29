package com.kyss.community.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * @ClassName WebConfig
 * @Description TODO
 * @Author davidt
 * @Date 6/12/2020 5:22 PM
 * @Version 1.0
 **/

@Configuration
//@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInteceptor loginInteceptor;

    @Autowired
    private NotificationInterceptor notificationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInteceptor).addPathPatterns("/**");
        registry.addInterceptor(notificationInterceptor).addPathPatterns("/**");
    }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/resources/**")
//                .addResourceLocations("/public", "classpath:/static/")
//                .setCachePeriod(31556926);
//    }
}
