package com.study.Ex14ReadDB.dto;

import com.study.Ex14ReadDB.domain.community.Community;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommunityResponseDto {
    private Long noticeIdx;
    private String noticeTitle;
    private String noticeMemberId;
    private String noticeContent;
    private LocalDate noticeDate;

    public CommunityResponseDto(Community community){
        this.noticeIdx = community.getNoticeIdx();
        this.noticeTitle = community.getNoticeTitle();
        this.noticeMemberId = community.getNoticeMemberId();
        this.noticeContent = community.getNoticeContent();
        this.noticeDate = community.getNoticeDate();
    }
}
