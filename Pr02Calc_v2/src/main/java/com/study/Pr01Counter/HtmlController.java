package com.study.Pr01Counter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class HtmlController {
    final Calculator calculator;

    @GetMapping("/")
    public String main(Model model) {
        return "index";
    }

    @GetMapping("/add")
    public String add(@RequestParam int num1, @RequestParam int num2, Model model) {
        int result = calculator.add(num1, num2);
        model.addAttribute("num1", num1);
        model.addAttribute("num2", num2);
        model.addAttribute("result", result);
        return "index";
    }

    @GetMapping("/sub")
    public String sub(@RequestParam int num1, @RequestParam int num2, Model model) {
        int result = calculator.sub(num1, num2);
        model.addAttribute("num1", num1);
        model.addAttribute("num2", num2);
        model.addAttribute("result", result);
        return "index";
    }

    @GetMapping("/mul")
    public String mul(@RequestParam int num1, @RequestParam int num2, Model model) {
        int result = calculator.mul(num1, num2);
        model.addAttribute("num1", num1);
        model.addAttribute("num2", num2);
        model.addAttribute("result", result);
        return "index";
    }

    @GetMapping("/div")
    public String div(@RequestParam int num1, @RequestParam int num2, Model model) {
        double result = calculator.div(num1, num2);
        model.addAttribute("num1", num1);
        model.addAttribute("num2", num2);
        model.addAttribute("result", result);
        return "index";
    }

}