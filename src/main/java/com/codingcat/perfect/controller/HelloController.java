package com.codingcat.perfect.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String hello(Model model){
        model.addAttribute("data", "hello");
        return "hello"; // viewResolver가 hello.html 찾아 보여줌
    }

    // MVC + 템플릿 엔진(타임리프, JSP)
    @GetMapping("/hello-mvc")
    public String helloMvc(@RequestParam String name, Model model){
        model.addAttribute("name", name);
        return "hello-template"; //
    }

    // API 방식(String) or JSON(Vue, React 등)
    @GetMapping("/hello-string")
    @ResponseBody
    public String helloString(@RequestParam String name){
        return "hello"+name;
    }
}
