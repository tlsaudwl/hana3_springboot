package com.study.Pr07LoginJoin_mine;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    List<Member> memberList = new ArrayList<>();

    @GetMapping("/")
    public String main() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String inputName, @RequestParam String inputPw, Model model) {
        System.out.println(inputName);
        System.out.println(inputPw);

        boolean isLoginOk = false;

        for (Member m : memberList) {
            if (m.getUsername().equals(inputName) && m.getPassword().equals(inputPw)) {
                isLoginOk = true;
                break;
            }
        }

        if (isLoginOk) {
            model.addAttribute("message", "로그인 성공!");
        }

        return "login";
    }

    @GetMapping("/join-form")
    public String joinForm() {
        return "join";
    }

    @ResponseBody
    @PostMapping("/check")
    public ResDto checkDuplicate(@RequestBody ReqDto reqDto) {
        String inputName = reqDto.getInputName();
        System.out.println("inputName = " + inputName);
        boolean isDulp = false;
        for (Member m : memberList) {
            if (m.getUsername().equals(inputName)) {
                isDulp = true;
                break;
            }
        }

        ResDto resDto = new ResDto();

        if (isDulp) {
            resDto.setMessage("중복된 아이디가 있습니다.");
        } else {
            resDto.setMessage("사용 가능한 아이디입니다.");
        }

        return resDto;
    }

    @PostMapping("/join")
    public String join(@ModelAttribute JoinDto joinDto) {
        memberList.add(Member.builder()
                .username(joinDto.getInputName()).email(joinDto.getInputEmail()).password(joinDto.getInputPw()).joindate(LocalDate.now())
                .build());
        return "redirect:/";
    }
}

