package com.kyss.community.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName CustomizeControllerAdvice
 * @Description TODO
 * @Author davidt
 * @Date 6/17/2020 11:12 AM
 * @Version 1.0
 **/

@ControllerAdvice
public class CustomizeControllerAdvice {

    @ExceptionHandler(Exception.class)
    ModelAndView handleControllerException(HttpServletRequest request, Throwable ex) {
        HttpStatus status = getStatus(request);
        ModelAndView modelAndView = new ModelAndView("/error", status);
        if (ex instanceof CustomizeException) {
            modelAndView.getModel().put("message", ex.getMessage());
        } else {
            if (status.is4xxClientError()) {
                StringBuilder msg = new StringBuilder(CustomizeErrorCode.PAGE_NOT_FOUND.getMessage());
                msg.append("\n");
                msg.append(ex.getMessage());
            } else if (status.is5xxServerError()) {
                StringBuilder msg = new StringBuilder(CustomizeErrorCode.SERVER_DEFAULT_ERROR.getMessage());
                msg.append("\n");
                msg.append(ex.getMessage());
                modelAndView.getModel().put("message", msg.toString());

            } else {
                modelAndView.getModel().put("message", ex.getMessage());
            }
        }
        return modelAndView;
    }

//    @ExceptionHandler
//    public String methodArgumentNotValid(BindException e, HttpServletRequest request) {
//        request.setAttribute("javax.servlet.error.status_code", 400);
//        request.setAttribute("code", 1);
//        request.setAttribute("message", "error page...");
//        return "forward:/error";
//    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
}
