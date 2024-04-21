
package com.study.Ex14ReadDB.controller;

import com.study.Ex14ReadDB.domain.community.Community;
import com.study.Ex14ReadDB.dto.CommunityResponseDto;
import com.study.Ex14ReadDB.dto.CommunitySaveRequestDto;
import com.study.Ex14ReadDB.dto.MemberResponseDto;
import com.study.Ex14ReadDB.service.AdminService;
import com.study.Ex14ReadDB.service.CommunityService;
import com.study.Ex14ReadDB.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;
    private final MemberService memberService;
    private final CommunityService communityService;

    @GetMapping("/admin")
    public String adminLogin(){
        return "/admin/admin_login";
    }

    @PostMapping("/adminLoginAction")
    public String adminLoginAction(@RequestParam String memberId, @RequestParam String memberPw, HttpSession session) {
        AdminService.LoginResult result = adminService.login(memberId, memberPw);
        if (result == AdminService.LoginResult.ID_NOT_FOUND) {
            return "redirect:/admin";
        } else if (result == AdminService.LoginResult.INVALID_PASSWORD) {
            return "redirect:/admin";
        } else {
            session.setAttribute("adminId", memberId);
            return "redirect:/adminMember";
        }
    }

    @GetMapping("/adminMember")
    public String adminMember(Model model) {
        long size = memberService.memberNum();
        List<MemberResponseDto> list = memberService.findAll();

        model.addAttribute("size", size);
        model.addAttribute("list", list);

        return "/admin/admin_member";
    }

    @GetMapping("/searchMember")
    public String searchMember(@RequestParam String searchSelect, @RequestParam String searchKeyword, HttpSession session, Model model) {
        session.setAttribute("searchSelect", searchSelect);
        session.setAttribute("searchKeyword", searchKeyword);

        List<MemberResponseDto> list = null;
        switch (searchSelect) {
            case "all":
                list = memberService.findMembersByKeyword(searchKeyword.toLowerCase());
                break;
            case "id":
                list = memberService.findMembersByMemberId(searchKeyword.toLowerCase());
                break;
            case "name":
                list = memberService.findMembersByMemberName(searchKeyword.toLowerCase());
                break;
            case "email":
                list = memberService.findMembersByMemberEmail(searchKeyword.toLowerCase());
                break;
            default:
                break;
        }
        int size = list != null ? list.size() : 0;
        model.addAttribute("list", list);
        model.addAttribute("size", size);

        return "/admin/admin_member";
    }

    @GetMapping("/sortMember")
    public String sortMember(@RequestParam String orderSelect, HttpSession session, Model model) {
        String searchSelect = (String) session.getAttribute("searchSelect");
        String searchKeyword = (String) session.getAttribute("searchKeyword");

        List<MemberResponseDto> list = null;
        switch (searchSelect) {
            case "all":
                list = memberService.findMembersByKeyword(searchKeyword.toLowerCase());
                break;
            case "id":
                list = memberService.findMembersByMemberId(searchKeyword.toLowerCase());
                break;
            case "name":
                list = memberService.findMembersByMemberName(searchKeyword.toLowerCase());
                break;
            case "email":
                list = memberService.findMembersByMemberEmail(searchKeyword.toLowerCase());
                break;
            default:
                break;
        }

        // 정렬된 결과 가져오기
        switch (orderSelect) {
            case "idAsc":
                list.sort(Comparator.comparing(MemberResponseDto::getMemberId));
                break;
            case "idDesc":
                list.sort(Comparator.comparing(MemberResponseDto::getMemberId).reversed());
                break;
            case "joinDateAsc":
                list.sort(Comparator.comparing(MemberResponseDto::getMemberJoinDate));
                break;
            case "joinDateDesc":
                list.sort(Comparator.comparing(MemberResponseDto::getMemberJoinDate).reversed());
                break;
            default:
                break;
        }

        int size = list != null ? list.size() : 0;
        model.addAttribute("list", list);
        model.addAttribute("size", size);
        model.addAttribute("selected", orderSelect);

        return "/admin/admin_member";
    }

    @GetMapping("/pageMember")
    public String pageMember(@RequestParam String pageSelect, @RequestParam String orderSelect, HttpSession session, Model model) {
        String searchSelect = (String) session.getAttribute("searchSelect");
        String searchKeyword = (String) session.getAttribute("searchKeyword");

        List<MemberResponseDto> list = null;
        switch (searchSelect) {
            case "all":
                list = memberService.findMembersByKeyword(searchKeyword.toLowerCase());
                break;
            case "id":
                list = memberService.findMembersByMemberId(searchKeyword.toLowerCase());
                break;
            case "name":
                list = memberService.findMembersByMemberName(searchKeyword.toLowerCase());
                break;
            case "email":
                list = memberService.findMembersByMemberEmail(searchKeyword.toLowerCase());
                break;
            default:
                break;
        }

        // 정렬된 결과 가져오기
        switch (orderSelect) {
            case "idAsc":
                list.sort(Comparator.comparing(MemberResponseDto::getMemberId));
                break;
            case "idDesc":
                list.sort(Comparator.comparing(MemberResponseDto::getMemberId).reversed());
                break;
            case "joinDateAsc":
                list.sort(Comparator.comparing(MemberResponseDto::getMemberJoinDate));
                break;
            case "joinDateDesc":
                list.sort(Comparator.comparing(MemberResponseDto::getMemberJoinDate).reversed());
                break;
            default:
                break;
        }

        // 페이징 처리
        List<MemberResponseDto> pageList;
        if (pageSelect.equals("page5")) {
            pageList = list.subList(0, Math.min(list.size(), 5)); // 상위 5개만 보기
        } else if (pageSelect.equals("page10")) {
            pageList = list.subList(0, Math.min(list.size(), 10)); // 상위 10개만 보기
        } else {
            pageList = list; // 전체 보기
        }

        int size = pageList.size();
        model.addAttribute("list", pageList);
        model.addAttribute("size", size);
        model.addAttribute("selected", orderSelect); // 정렬 상태 유지

        return "/admin/admin_member";
    }


    @GetMapping("/adminNotice")
    public String adminNotice(Model model) {
        long size = communityService.communityNum();
        List<CommunityResponseDto> notices = communityService.getAllNotices();

        model.addAttribute("size", size);
        model.addAttribute("notices", notices);

        return "/admin/admin_notice";
    }

    @GetMapping("/admin/searchNotice")
    public String searchNotice(@RequestParam String searchSelect, @RequestParam String searchKeyword, HttpSession session, Model model) {
        session.setAttribute("searchSelect", searchSelect);
        session.setAttribute("searchKeyword", searchKeyword);

        List<CommunityResponseDto> notices = null;
        switch (searchSelect) {
            case "all":
                notices = communityService.findNoticesByKeyword(searchKeyword.toLowerCase());
                break;
            case "title":
                notices = communityService.findNoticesByNoticeTitle(searchKeyword.toLowerCase());
                break;
            case "content":
                notices = communityService.findNoticesByNoticeContent(searchKeyword.toLowerCase());
                break;
            case "id":
                notices = communityService.findNoticesByNoticeId(searchKeyword.toLowerCase());
                break;
            default:
                break;
        }
        model.addAttribute("notices", notices);

        return "/admin/admin_notice";
    }

    @GetMapping("/sortNotice")
    public String sortNotice(@RequestParam(required = false) String orderSelect, HttpSession session, Model model) {
        String searchSelect = (String) session.getAttribute("searchSelect");
        String searchKeyword = (String) session.getAttribute("searchKeyword");

        List<CommunityResponseDto> notices;

        // 검색 옵션과 키워드가 있는 경우에는 해당 조건에 맞는 공지사항을 검색
        if (searchSelect != null && searchKeyword != null) {
            switch (searchSelect) {
                case "all":
                    notices = communityService.findNoticesByKeyword(searchKeyword.toLowerCase());
                    break;
                case "title":
                    notices = communityService.findNoticesByNoticeTitle(searchKeyword.toLowerCase());
                    break;
                case "content":
                    notices = communityService.findNoticesByNoticeContent(searchKeyword.toLowerCase());
                    break;
                case "id":
                    notices = communityService.findNoticesByNoticeId(searchKeyword.toLowerCase());
                    break;
                default:
                    notices = Collections.emptyList();
                    break;
            }
        } else {
            // 검색 옵션과 키워드가 없는 경우 전체 공지사항을 가져옴
            notices = communityService.getAllNotices();
        }

        if (orderSelect == null) {
            orderSelect = "idAsc"; // 기본값 설정
        }

        // 정렬된 결과 가져오기
        switch (orderSelect) {
            case "idAsc":
                notices.sort(Comparator.comparing(CommunityResponseDto::getNoticeIdx));
                break;
            case "idDesc":
                notices.sort(Comparator.comparing(CommunityResponseDto::getNoticeIdx).reversed());
                break;
            case "regDateAsc":
                notices.sort(Comparator.comparing(CommunityResponseDto::getNoticeDate));
                break;
            case "regDateDesc":
                notices.sort(Comparator.comparing(CommunityResponseDto::getNoticeDate).reversed());
                break;
            default:
                break;
        }

        model.addAttribute("notices", notices);
        model.addAttribute("size", notices.size());
        model.addAttribute("selected", orderSelect);

        return "/admin/admin_notice";
    }

    @GetMapping("/pageNotice")
    public String pageNotice(@RequestParam String pageSelect, @RequestParam String orderSelect, HttpSession session, Model model) {
        String searchSelect = (String) session.getAttribute("searchSelect");
        String searchKeyword = (String) session.getAttribute("searchKeyword");

        List<CommunityResponseDto> notices = null;
        switch (searchSelect) {
            case "all":
                notices = communityService.findNoticesByKeyword(searchKeyword.toLowerCase());
                break;
            case "title":
                notices = communityService.findNoticesByNoticeTitle(searchKeyword.toLowerCase());
                break;
            case "content":
                notices = communityService.findNoticesByNoticeContent(searchKeyword.toLowerCase());
                break;
            case "id":
                notices = communityService.findNoticesByNoticeId(searchKeyword.toLowerCase());
                break;
            default:
                break;
        }

        // 검색 결과가 없을 때 전체 목록을 가져옴
        if (notices == null || notices.isEmpty()) {
            notices = communityService.getAllNotices(); // 전체 회원 목록 가져오기
        }

        // 정렬된 결과 가져오기
        switch (orderSelect) {
            case "idAsc":
                notices.sort(Comparator.comparing(CommunityResponseDto::getNoticeIdx));
                break;
            case "idDesc":
                notices.sort(Comparator.comparing(CommunityResponseDto::getNoticeIdx).reversed());
                break;
            case "joinDateAsc":
                notices.sort(Comparator.comparing(CommunityResponseDto::getNoticeDate));
                break;
            case "joinDateDesc":
                notices.sort(Comparator.comparing(CommunityResponseDto::getNoticeDate).reversed());
                break;
            default:
                break;
        }

        // 페이징 처리
        List<CommunityResponseDto> pageList;
        if (pageSelect.equals("page5")) {
            pageList = notices.subList(0, Math.min(notices.size(), 5)); // 상위 5개만 보기
        } else if (pageSelect.equals("page10")) {
            pageList = notices.subList(0, Math.min(notices.size(), 10)); // 상위 10개만 보기
        } else {
            pageList = notices; // 전체 보기
        }

        int size = pageList.size();
        model.addAttribute("notices", pageList);
        model.addAttribute("size", size);
        model.addAttribute("selected", orderSelect); // 정렬 상태 유지

        return "/admin/admin_notice";
    }

    @GetMapping("/writeNotice")
    public String writeNotice() {
        return "/admin/admin_notice_write";
    }


    @PostMapping("/insertNotice")
    @ResponseBody
    public String insertNotice(@ModelAttribute CommunitySaveRequestDto dto, HttpSession session) {
        String adminId = (String) session.getAttribute("adminId");
        if (adminId == null) {
        // 로그인하지 않은 경우 등의 처리
        return "<script>alert('로그인 후 이용해주세요.'); location.href='/admin';</script>";
    }

    dto.setNoticeMemberId(adminId);
    boolean isInsert = communityService.save(dto.toSaveEntity());
    if (isInsert) {
        return "<script>alert('공지사항 등록 성공'); location.href='/adminNotice';</script>";
    } else {
        return "<script>alert('공지사항 등록 실패'); history.back();</script>";
        }
    }


    @GetMapping("/modifyNotice")
    public String modifyNotice(@RequestParam Long noticeIdx, Model model) {
        CommunityResponseDto dto = communityService.findById(noticeIdx);
        model.addAttribute("notice", dto);
        return "/admin/admin_notice_view";
    }

    @PostMapping("/modifyNoticeAction")
    @ResponseBody
    public String modifyNoticeAction(@RequestParam Long noticeIdx, @RequestParam String editor4) {
        String updateResult = communityService.update(noticeIdx, editor4);
        if (updateResult.equals("success")) {
            return "<script>alert('공지사항 수정 성공'); location.href='/adminNotice';</script>";
        }else {
            return "<script>alert('공지사항 수정 실패'); history.back();</script>";
        }
    }


}
