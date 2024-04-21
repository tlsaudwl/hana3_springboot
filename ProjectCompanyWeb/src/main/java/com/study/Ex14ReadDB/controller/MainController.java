package com.study.Ex14ReadDB.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    @GetMapping("/")
    public String main(){
        return "index"; //index.html로 응답
    }


    @GetMapping("/company/company01")
    public String company01(){
        return "/company/company01";
    }
    @GetMapping("/company/company03")
    public String company03(){
        return "/company/company03";
    }
    @GetMapping("/business/business01")
    public String business01(){
        return "/business/business01";
    }
    @GetMapping("/product/product01")
    public String product01(){
        return "/product/product01";
    }

}