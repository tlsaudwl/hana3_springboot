package com.study.Ex14ReadDB.controller;

import com.study.Ex14ReadDB.dto.MemberSaveDto;
import com.study.Ex14ReadDB.entity.MemberEntity;
import com.study.Ex14ReadDB.entity.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MainController {
    //생성자 주입
    final MemberRepository memberRepository;

    @GetMapping("/")
    public String main(){
        return "redirect:/list";
    }

    @RequestMapping("/list")
    public String list(Model model){
        List<MemberEntity> list =
                memberRepository.findAll();
        model.addAttribute("list", list);
        System.out.println("size:" + list.size());
        model.addAttribute("listcount", memberRepository.count() );

        return "index"; //index.html로 응답
    }
    @GetMapping("/joinForm")
    public String joinForm(){
        return "joinForm"; //joinForm.html로 응답
    }
    @PostMapping("/joinAction")
    @ResponseBody
    public String joinAction(@ModelAttribute MemberSaveDto dto){
        System.out.println("name:"+dto.getUserName());
        try {
            MemberEntity memberEntity = dto.toSaveEntity();
            memberRepository.save(memberEntity);
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("회원가입 실패");
            return "<script>alert('회원가입 실패');history.back();</script>";
        }
        System.out.println("회원가입 성공");
        return "<script>alert('회원가입 성공');location.href='/list';</script>";
    }
    //viewDTO?id=1
    @GetMapping("/viewMember")
    public String viewMember(@RequestParam int id, Model model){
        Optional<MemberEntity> optional = memberRepository.findById( (long)id );
        if( !optional.isPresent() ){
            System.out.println("회원정보가 없습니다.");
            return "redirect:/list";
        }
        optional.ifPresent( memberEntity -> {
            model.addAttribute( "member", memberEntity.toSaveDto());
            System.out.println("userName: " + memberEntity.getUserName());
        });
        return "modifyForm"; //modifyForm.html로 응답
    }
    @PostMapping("/modifyAction")
    @ResponseBody
    public String modifyAction(@ModelAttribute MemberSaveDto dto){
        try{
            MemberEntity memberEntity = dto.toUpdateEntity();
            memberRepository.save( memberEntity );
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("회원정보수정 실패");
            return "<script>alert('회원정보수정 실패');history.back();</script>";
        }
        System.out.println("회원정보수정 성공");
        return "<script>alert('회원정보수정 성공');location.href='/list';</script>";
    }
    @GetMapping("/deleteMember")
    @ResponseBody
    public String deleteMember(@RequestParam int id){
        Optional<MemberEntity> optional = memberRepository.findById((long)id);
        if( !optional.isPresent() ){
            System.out.println("회원정보조회 실패");
            return "<script>alert('회원정보조회 실패');history.back();</script>";
        }
        MemberEntity memberEntity = optional.get();
        try{
            memberRepository.delete( memberEntity );
        }catch (Exception e){
            e.printStackTrace();
            return "<script>alert('회원정보삭제 실패');history.back();</script>";
        }
        return "<script>alert('회원정보삭제 성공');location.href='/list';</script>";
    }
}