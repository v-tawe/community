package com.kyss.community.exception;

import org.springframework.boot.web.servlet.error.ErrorController;

/**
 * @ClassName CustomizeExceptionHandler
 * @Description TODO
 * @Author davidt
 * @Date 6/17/2020 3:15 PM
 * @Version 1.0
 **/
public class CustomizeErrorController implements ErrorController {

    @Override
    public String getErrorPath() {
        return "error";
    }
}
