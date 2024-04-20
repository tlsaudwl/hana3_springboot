package com.study.Ex14ReadDB.dto;

import com.study.Ex14ReadDB.domain.community.Community;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class CommunitySaveRequestDto {
    private String noticeTitle;
    private String noticeContent;
    private String noticeMemberId;
    private LocalDate noticeDate = LocalDate.now();

    @Builder
    public CommunitySaveRequestDto(String noticeTitle, String noticeContent,
                                   String noticeMemberId, LocalDate noticeDate) {
        this.noticeTitle = noticeTitle;
        this.noticeContent = noticeContent;
        this.noticeMemberId = noticeMemberId;
        this.noticeDate = noticeDate;
    }

    public Community toSaveEntity(){
        return Community.builder()
                .noticeTitle(noticeTitle)
                .noticeContent(noticeContent)
                .noticeMemberId(noticeMemberId)
                .noticeDate(noticeDate)
                .build();
    }
}
