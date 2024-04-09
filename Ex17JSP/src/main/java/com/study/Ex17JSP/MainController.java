package com.study.Ex17JSP;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MainController {
    @GetMapping("/")
    public String main(){
        return "ex01";  // ex01.jsp 응답함
    }
    @GetMapping("/ex/{no}")
    // url : localhost:8080/ex/02
    public String ex(@PathVariable String no){
        return "ex" + no;  // ex02.jsp 응답
    }
}
