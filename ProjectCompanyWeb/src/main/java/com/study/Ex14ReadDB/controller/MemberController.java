package com.study.Ex14ReadDB.controller;


import com.study.Ex14ReadDB.domain.member.Member;
import com.study.Ex14ReadDB.dto.*;
import com.study.Ex14ReadDB.service.MemberService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.LoginException;
import java.util.Optional;

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

    //로그인
    @GetMapping("/login")
    public String login(){
        return "/member/login";
    }
//    @PostMapping("/loginAction")
//    @ResponseBody
//    public String loginAction(HttpSession session, @RequestParam String loginId, @RequestParam String loginPw) {
//        boolean findId = memberService.findByMemberId(loginId);
//        if (!findId) {
//            return "<script>alert('아이디가 존재하지 않습니다.'); history.back(); </script>";
//        }
//
//        boolean findPw = memberService.findByMemberPw(loginPw);
//        if (!findPw) {
//            return "<script>alert('비밀번호가 다릅니다.'); history.back(); </script>";
//        }
//
//        MemberResponseDto member = memberService.findByMemberIdAndMemberPw(loginId, loginPw);
//        session.setAttribute("loginId", member.getMemberId());
//
//        return "<script>alert('로그인되었습니다.'); location.href='/'; </script>";
//    }

    @PostMapping("/loginAction")
    public ResponseEntity<String> loginAction(HttpSession session, @RequestParam String loginId, @RequestParam String loginPw) {
        MemberService.LoginResult result = memberService.login(loginId, loginPw);
        if (result == MemberService.LoginResult.ID_NOT_FOUND) {
            return ResponseEntity.ok("<script>alert('아이디가 존재하지 않습니다'); location.href='/member/login';</script>");
        } else if (result == MemberService.LoginResult.INVALID_PASSWORD) {
            return ResponseEntity.ok("<script>alert('비밀번호가 다릅니다'); location.href='/member/login';</script>");
        }

        session.setAttribute("loginId", loginId);
        return ResponseEntity.ok("<script>alert('로그인되었습니다'); location.href='/';</script>");
    }

    @GetMapping("/logout")
    @ResponseBody
    public String logout(HttpSession session){
        //로그아웃 액션
        session.setAttribute("isLogin", null);
        session.setAttribute("userId", null);

        session.invalidate(); //세션종료(JSESSIONID 종료), 모든 속성 제거됨.

        return "<script>alert('로그아웃되었습니다.'); location.href='/';</script>";
    }

    //아이디 찾기
    @GetMapping("/idFind")
    public String idFind(){
        return "/member/idFind"; //idFind.html 응답
    }
    @PostMapping("/idFindAction")
    public String idFindAction(@RequestParam String userName, @RequestParam String userEmail, Model model) {
        String result = memberService.findIdByMemberNameAndMemberEmail(userName, userEmail);
        model.addAttribute("userId", result);

        return "/member/idFind";
    }

}