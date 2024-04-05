package com.study.Pr07LoginJoin_mine;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class RestApiController {
    List<Member> memberList = new ArrayList<>();

    @PostMapping("/login")
    public ResDto login(@RequestBody LoginDto dto) {
        boolean isLoginOk = false;

        for (Member m : memberList) {
            if (m.getUsername().equals(dto.getInputName()) && m.getPassword().equals(dto.getInputPw())) {
                isLoginOk = true;
                break;
            }
        }
        if (isLoginOk) {
            return ResDto.builder()
                    .status("ok")
                    .message("로그인 성공!")
                    .build();
        } else {

            return ResDto.builder()
                    .status("fail")
                    .message("로그인 실패")
                    .build();
        }

    }

    @PostMapping("/check")
    public ResDto checkDupl(@RequestBody ReqDto reqDto) {
        String inputName = reqDto.getInputName();
        System.out.println("inputName = " + inputName);
        boolean isDupl = false;
        for (Member m : memberList) {
            if (m.getUsername().equals(inputName)) {
                isDupl = true;
                break;
            }
        }

        if (isDupl) {
            return ResDto.builder()
                    .message("중복된 아이디가 있습니다.")
                    .build();
        } else {
            return ResDto.builder()
                    .message("사용 가능한 아이디입니다.")
                    .build();
        }

    }

    @PostMapping("/join")
    public ResDto join(@RequestBody JoinDto dto) {
        boolean isJoin = false;

        memberList.add(Member.builder()
                .username(dto.getInputName()).password(dto.getInputPw()).email(dto.getInputEmail()).joindate(LocalDate.now())
                .build());
        return ResDto.builder()
                .status("ok")
                .message("회원가입 성공")
                .build();
    }
}
