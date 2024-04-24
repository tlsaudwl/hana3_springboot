package com.study.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
    @GetMapping("/")
    public String main() {
        return "index"; //index.html로 응답
    }
    @GetMapping("/loginForm")
    public String loginForm(){
        return "loginForm"; //loginForm.html로 응답
    }

}
