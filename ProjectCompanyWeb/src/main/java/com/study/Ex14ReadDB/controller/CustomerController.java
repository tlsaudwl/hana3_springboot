package com.study.Ex14ReadDB.controller;

import com.study.Ex14ReadDB.dto.CommunityResponseDto;
import com.study.Ex14ReadDB.dto.CustomerQnaDto;
import com.study.Ex14ReadDB.dto.CustomerSaveRequestDto;
import com.study.Ex14ReadDB.service.CustomerQnaService;
import com.study.Ex14ReadDB.service.CustomerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {
    final private CustomerService customerService;
    final private CustomerQnaService customerQnaService;

    //1:1문의사항 글쓰기
    @GetMapping("/customer01")
    @ResponseBody
    public String customer01(HttpSession session) {
        if (session.getAttribute("loginId") == null) {
            return "<script>alert('로그인을 해주세요'); location.href='/member/login';</script>";
        }
        return "<script>location.href='/customer/one2one';</script>";
    }
    @GetMapping("/one2one")
    public String one2one() {
        return "/customer/customer01";
    }
    @PostMapping("/one2one_insert")
    @ResponseBody
    public String one2one_insert(@ModelAttribute CustomerSaveRequestDto dto,
                                 @RequestParam String one2one_name,
                                 @RequestParam String one2one_phone,
                                 @RequestParam String one2one_email,
                                 @RequestParam String one2one_title,
                                 @RequestParam String one2one_content,
                                 @RequestParam String sample6_address,
                                 @RequestParam String sample6_detailAddress,
                                 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam LocalDate one2one_date) {

        // 사용자가 입력한 값들을 dto에 설정
        dto.setOne2oneName(one2one_name);
        dto.setOne2onePhone(one2one_phone);
        dto.setOne2oneEmail(one2one_email);
        dto.setOne2oneTitle(one2one_title);
        dto.setOne2oneContent(one2one_content);

        // 주소 설정
        String address = sample6_address + " " + sample6_detailAddress;
        dto.setOne2oneAddress(address);

        // 날짜 설정
        dto.setOne2oneDate(LocalDate.now());

        // 데이터베이스에 저장
        Long newOne2oneIdx = customerService.save(dto);
        boolean isFound = customerService.existsById(newOne2oneIdx);
        if (isFound) {
            return "<script>alert('1:1 문의글 등록 성공'); location.href='/';</script>";
        } else {
            return "<script>alert('1:1 문의글 등록 실패'); history.back();</script>";
        }
    }


    //묻고답하기
    @GetMapping("/customer02")
    @ResponseBody
    public String customer02(HttpSession session) {
        if (session.getAttribute("loginId") == null) {
            return "<script>alert('로그인을 해주세요'); location.href='/member/login';</script>";
        }
        return "<script>location.href='/customer/qna';</script>";
    }
    @GetMapping("/qna")
    public String qna(Model model) {
        model.addAttribute("qnas", customerQnaService.getAllNotices());
        return "/customer/customer02";
    }
    @GetMapping("/search")
    public String search(@RequestParam(required = false) String searchType, @RequestParam String keyword, Model model) {
        List<CustomerQnaDto> searchResult = null;

        if (searchType == null || searchType.isEmpty()) {
            searchResult = customerQnaService.getAllNotices();
        } else {
            if (searchType.equals("qnaTitle")) {
                searchResult = customerQnaService.searchByQnaTitle(keyword);
            } else if (searchType.equals("qnaContent")) {
                searchResult = customerQnaService.searchByQnaContent(keyword);
            } else if (searchType.equals("qnaName")) {
                searchResult = customerQnaService.searchByQnaName(keyword);
            }
        }

        model.addAttribute("qnas", searchResult);

        return "/customer/customer02";
    }
    @GetMapping("/customer02_2")
    public String customer02_2(){
        return "/customer/customer02_2";
    }

    @GetMapping("/customer02_3")
    public String customer02_3(HttpServletRequest request, Model model) {
        Long no = Long.parseLong(request.getParameter("no"));
        model.addAttribute("qnaIdx", no);
        return "/customer/customer02_3";
    }

    @PostMapping("/qnaPwCheck")
    @ResponseBody
    public String qnaPwCheck(@RequestParam String qnaPw, @RequestParam Long qnaIdx) {
        CustomerQnaDto dto = customerQnaService.findById(qnaIdx);
        if (dto != null && dto.getQnaPw().equals(qnaPw)) {
            return "<script>location.href='/customer/qnaDetail?no=" + dto.getQnaIdx() + "';</script>";
        }

        return "<script>alert('비밀번호가 일치하지 않습니다.'); history.back(); </script>";
    }

    @GetMapping("/qnaDetail")
    public String qnaDetail(HttpServletRequest request, Model model) {
        Long qnaIdx = Long.parseLong(request.getParameter("no"));
        CustomerQnaDto dto = customerQnaService.findById(qnaIdx);
        model.addAttribute("qna", dto);

        return "/customer/customer02_4";
    }


    @GetMapping("/customer03")
    @ResponseBody
    public String customer03(HttpSession session) {
        if (session.getAttribute("loginId") == null) {
            return "<script>alert('로그인을 해주세요'); location.href='/member/login';</script>";
        }
        return "<script>location.href='/customer/one2one';</script>";
    }


}
