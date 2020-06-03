package com.kyss.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName HomeController
 * @Description TODO
 * @Author davidt
 * @Date 6/3/2020 5:53 PM
 * @Version 1.0
 **/

@Controller
public class HomeController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }
}
