package com.codingcat.perfect.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // 컨트룰러가 index.html보다 우선순위가 높다.
    @GetMapping("/")
    public String home(){
        return "home";
    }
}
