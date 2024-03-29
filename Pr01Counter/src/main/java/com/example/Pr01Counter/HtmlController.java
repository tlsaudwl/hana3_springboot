package com.example.Pr01Counter;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HtmlController {

    private final Counter counter;
    private final Member member;

//    public HtmlController(Counter counter, Member member){
//        this.counter = counter;
//        this.member = member;
//    }

    @GetMapping("/")
    public String main(Model model){
        System.out.println("counter:"+counter);
        System.out.println("member:"+member);
        model.addAttribute("count", counter.getCount());
        return "index";
    }
    @GetMapping("/plus")
    public String plus(){
        counter.setCount( counter.getCount() + 1 );
        return "redirect:/";
    }
    @GetMapping("/minus")
    public String minus(){
        counter.setCount( counter.getCount() - 1 );
        return "redirect:/";
    }
}
