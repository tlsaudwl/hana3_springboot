package com.study.Ex09RestAPI;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MissingMatrixVariableException;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;

@Controller
public class HtmlController {
    @GetMapping("/")
    public String main(){
        // 첫화면 진입시 데이터 초기화한다
        Member member = new Member("hong", "1234", "abc@dot.com",
                LocalDate.now());
        Member member1 = Member.builder()
                .username("hong")
                .password(("1234"))
                .email("abc@dot.com")
                .joindate(LocalDate.now()).build();
        // member보다는 member1 선호
        ApiController.memberList.add(member);

        return "login"; // login.html로 응답
    }
}
