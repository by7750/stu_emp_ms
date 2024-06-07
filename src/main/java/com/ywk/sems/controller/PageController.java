package com.ywk.sems.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin
public class PageController {

    @GetMapping("/login")
    public String login() {
        return "pages/login/login.html";
    }



}
