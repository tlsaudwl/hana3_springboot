
package com.study.Pr07LoginJoin_mine;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {


    @GetMapping("/")
    public String main() {
        return "login";
    }

    @GetMapping("/join-form")
    public String joinForm() {
        return "join";
    }

    @GetMapping("/login")
    public String login() {
        return "redirect:/";
    }
}
