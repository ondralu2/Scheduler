package com.project.scheduler.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GeneralController {

    @RequestMapping("/")
    public String showHomepage(){
        return "index";
    }
}
