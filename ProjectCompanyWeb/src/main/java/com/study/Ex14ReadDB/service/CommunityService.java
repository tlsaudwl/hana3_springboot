package com.study.Ex14ReadDB.service;

import com.study.Ex14ReadDB.domain.community.Community;
import com.study.Ex14ReadDB.domain.community.CommunityRepository;
import com.study.Ex14ReadDB.domain.member.Member;
import com.study.Ex14ReadDB.dto.CommunityResponseDto;
import com.study.Ex14ReadDB.dto.CommunitySaveRequestDto;
import com.study.Ex14ReadDB.dto.MemberResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
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

    @Transactional(readOnly = true)
    public long communityNum() {
        return communityRepository.count();
    }


    @Transactional(readOnly = true)
    public List<CommunityResponseDto> findNoticesByKeyword(String searchKeyword) {
        List<Community> list = communityRepository.findNoticesByKeyword(searchKeyword.toLowerCase());
        return list.stream().map(CommunityResponseDto::new).collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    public List<CommunityResponseDto> findNoticesByNoticeTitle(String searchKeyword) {
        List<Community> list = communityRepository.findNoticesByNoticeTitle(searchKeyword.toLowerCase());
        return list.stream().map(CommunityResponseDto::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<CommunityResponseDto> findNoticesByNoticeContent(String searchKeyword) {
        List<Community> list = communityRepository.findNoticesByNoticeContent(searchKeyword.toLowerCase());
        return list.stream().map(CommunityResponseDto::new).collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    public List<CommunityResponseDto> findNoticesByNoticeId(String searchKeyword) {
        List<Community> list = communityRepository.findNoticesByNoticeId(searchKeyword.toLowerCase());
        return list.stream().map(CommunityResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public boolean save(Community entity) {
        Community notice = communityRepository.save(entity);
        return notice.getNoticeIdx() != 0;
    }

//    @Transactional(readOnly = true)
//    public CommunityResponseDto findById(Long no) {
//        Optional<Community> optional = communityRepository.findById(no);
//        if (optional.isPresent()) {
//            return CommunityResponseDto.builder()
//                    .noticeIdx(optional.get().getNoticeIdx())
//                    .noticeMemberId(optional.get().getNoticeMemberId())
//                    .noticeTitle(optional.get().getNoticeTitle())
//                    .noticeContent(optional.get().getNoticeContent())
//                    .noticeDate(optional.get().getNoticeDate())
//                    .build();
//        } else {
//            return null;
//        }
//    }
//
//    @Transactional
//    public String update(Long noticeIdx, String noticeContent) {
//        Optional<Community> optionalCommunity = communityRepository.findById(noticeIdx);
//        if (optionalCommunity.isPresent()) {
//            Community community = optionalCommunity.get();
//            community.setNoticeContent(noticeContent); // 업데이트할 내용 설정
//            communityRepository.save(community); // 저장하여 업데이트 수행
//            return "success";
//        } else {
//            return "해당 엔티티를 찾을 수 없습니다.";
//        }
//    }

}


