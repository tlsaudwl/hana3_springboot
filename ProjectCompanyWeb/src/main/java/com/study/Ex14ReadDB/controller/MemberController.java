package com.study.Ex14ReadDB.controller;


import com.study.Ex14ReadDB.domain.member.Member;
import com.study.Ex14ReadDB.dto.MemberIdDuplicateDto;
import com.study.Ex14ReadDB.dto.MemberIdDuplicateRequestDto;
import com.study.Ex14ReadDB.dto.MemberSaveRequestDto;
import com.study.Ex14ReadDB.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    //회원가입
    @GetMapping("/join")
    public String join(){
        return "/member/join2";
    }
    @PostMapping("/joinAction")
    @ResponseBody
    public String joinAction(@ModelAttribute MemberSaveRequestDto dto,
                             BindingResult bindingResult){
        if( bindingResult.hasErrors() ){
            //바인딩 오류
            //DTO에 설정한 message값을 가져온다.
            String detail = bindingResult.getFieldError().getDefaultMessage();
            //DTO에 유효성체크를 걸어놓은 어노테이션명을 가져온다.
            String bindResultCode = bindingResult.getFieldError().getCode();
            System.out.println( detail + ":" + bindResultCode);

            return "<script>alert('" + detail +"'); history.back();</script>";
        }

        try {
            Member member = dto.toSaveEntity();
            memberService.save(dto);
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("회원가입 실패");
            return "<script>alert('회원가입 실패');history.back();</script>";
        }
        System.out.println("회원가입 성공");
        return "<script>alert('회원가입 성공');location.href='/';</script>";
    }

    //아이디중복체크
    @PostMapping("/checkDuplicateId")
    @ResponseBody
    public MemberIdDuplicateDto checkDuplicateId(@RequestBody MemberIdDuplicateRequestDto dto) {
        System.out.println(dto.getUserId());
        boolean isDuplicate = memberService.findByMemberId(dto.getUserId());

        if (isDuplicate) {
            return MemberIdDuplicateDto.builder()
                    .status("fail")
                    .build();
        } else {
            return MemberIdDuplicateDto.builder()
                    .status("ok")
                    .build();
        }
    }

}