package com.study.Ex14ReadDB.dto;

import com.study.Ex14ReadDB.domain.member.Member;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberResponseDto {
    private Long memberIdx;
    private String memberId;
    private String memberName;
    private String memberEmail;
    private String memberGender;
    private LocalDate memberBirthDate;
    private LocalDate memberJoinDate;

    public MemberResponseDto(Member member) {
        this.memberIdx = member.getMemberIdx();
        this.memberId = member.getMemberId();
        this.memberName = member.getMemberName();
        this.memberEmail = member.getMemberEmail();
        this.memberGender = member.getMemberGender();
        this.memberBirthDate = member.getMemberBirthDate();
        this.memberJoinDate = member.getMemberJoinDate();
    }
}
