package com.study.Ex14ReadDB.service;

import com.study.Ex14ReadDB.domain.community.Community;
import com.study.Ex14ReadDB.domain.community.CommunityRepository;
import com.study.Ex14ReadDB.dto.CommunityResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommunityService {
    @Autowired
    private final CommunityRepository communityRepository;

    @Transactional(readOnly = true)
    public List<CommunityResponseDto> getAllNotices() {
        List<Community> notices = communityRepository.findAll();
        return notices.stream()
                .map(CommunityResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<CommunityResponseDto> searchByTitle(String keyword) {
        List<Community> communities = communityRepository.findByNoticeTitleContaining(keyword);
        return communities.stream()
                .map(CommunityResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<CommunityResponseDto> searchByContent(String keyword) {
        List<Community> communities = communityRepository.findByNoticeContentContaining(keyword);
        return communities.stream()
                .map(CommunityResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CommunityResponseDto getNoticeDetail(Long noticeIdx) {
        Community notice = communityRepository.findById(noticeIdx)
                .orElseThrow(() -> new IllegalArgumentException("해당 공지사항을 찾을 수 없습니다."));
        return new CommunityResponseDto(notice);
    }
}
