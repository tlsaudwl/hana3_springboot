package com.study.Ex14ReadDB.controller;

import com.study.Ex14ReadDB.domain.community.Community;
import com.study.Ex14ReadDB.dto.CommunityResponseDto;
import com.study.Ex14ReadDB.service.CommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/community")
@RequiredArgsConstructor
public class CommunityController {
    final private CommunityService communityService;

    @GetMapping("/community01")
    public String community01(Model model) {
        model.addAttribute("notices", communityService.getAllNotices());
        return "/community/community01";
    }
    @GetMapping("/noticeDetail")
    public String noticeDetail(@RequestParam("notice_idx") Long noticeIdx, Model model) {
        CommunityResponseDto noticeDetail = communityService.getNoticeDetail(noticeIdx);
        model.addAttribute("noticeDetail", noticeDetail);
        return "/community/community01_1";
    }
    @GetMapping("/search")
    public String search(@RequestParam String searchType, @RequestParam String keyword, Model model) {
        List<CommunityResponseDto> searchResult;
        if (searchType.equals("title")) {
            searchResult = communityService.searchByTitle(keyword);
        } else {
            searchResult = communityService.searchByContent(keyword);
        }
        model.addAttribute("notices", searchResult);

        return "/community/community01";
    }

}
