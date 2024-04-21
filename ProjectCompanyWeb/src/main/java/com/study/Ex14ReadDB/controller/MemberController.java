package com.study.Ex14ReadDB.controller;


import com.study.Ex14ReadDB.domain.member.Member;
import com.study.Ex14ReadDB.dto.*;
import com.study.Ex14ReadDB.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
            String detail = bindingResult.getFieldError().getDefaultMessage();
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

    //로그인, 로그아웃
    @GetMapping("/login")
    public String login(){
        return "/member/login";
    }

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
        session.setAttribute("isLogin", null);
        session.setAttribute("userId", null);

        session.invalidate();

        return "<script>alert('로그아웃되었습니다.'); location.href='/';</script>";
    }

    //아이디 찾기
    @GetMapping("/idFind")
    public String idFind(){
        return "/member/idFind";
    }
    @PostMapping("/idFindAction")
    public String idFindAction(@RequestParam String userName, @RequestParam String userEmail, Model model) {
        String idFind = memberService.findIdByMemberNameAndMemberEmail(userName, userEmail);
        model.addAttribute("userId", idFind);

        return "/member/idFind";
    }

    //비밀번호 찾기
    @GetMapping("/passwordFind")
    public String passwordFind(){
        return "/member/passwordFind";
    }
    @PostMapping("/passwordFindAction")
    public String passwordFindAction(@RequestParam String userName, @RequestParam String userEmail, @RequestParam String userID, Model model) {
        String pwFind = memberService.findPwByMemberNameAndMemberEmailAndMemberId(userName, userEmail, userID);
        model.addAttribute("userPw", pwFind);

        return "/member/passwordFind";
    }

}