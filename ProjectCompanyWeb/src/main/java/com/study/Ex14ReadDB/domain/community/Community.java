package com.study.Ex14ReadDB.domain.community;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "company_notice")
public class Community {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_idx", nullable = false)
    private Long noticeIdx;
    @Column(name = "notice_title", nullable = false)
    private String noticeTitle;
    @Column(name = "notice_content", nullable = false)
    private String noticeContent;
    @Column(name = "notice_member_id", nullable = false)
    private String noticeMemberId;
    @Column(name = "notice_date", nullable = false)
    private LocalDate noticeDate = LocalDate.now();

    @Builder
    public Community(String noticeTitle, String noticeContent,
                     String noticeMemberId, LocalDate noticeDate) {
        this.noticeTitle = noticeTitle;
        this.noticeContent = noticeContent;
        this.noticeMemberId = noticeMemberId;
        this.noticeDate = noticeDate;
    }
}
