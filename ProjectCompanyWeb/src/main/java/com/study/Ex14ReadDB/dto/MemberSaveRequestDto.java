package com.study.Ex14ReadDB.dto;

import com.study.Ex14ReadDB.domain.member.Member;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class MemberSaveRequestDto {
    private String memberId;
    private String memberPw;
    private String memberName;
    private String memberEmail;
    private int memberEmailReceive;
    private int memberPwQuestion;
    private String memberPwAnswer;
    private String memberGender;
    private LocalDate memberBirthDate;
    private LocalDate memberJoinDate = LocalDate.now();

    @Builder
    public MemberSaveRequestDto(String memberId, String memberPw, String memberName, String memberEmail,
                                int memberEmailReceive, int memberPwQuestion, String memberPwAnswer,
                                String memberGender, LocalDate memberBirthDate, LocalDate memberJoinDate) {
        this.memberId = memberId;
        this.memberPw = memberPw;
        this.memberName = memberName;
        this.memberEmail = memberEmail;
        this.memberEmailReceive = memberEmailReceive;
        this.memberPwQuestion = memberPwQuestion;
        this.memberPwAnswer = memberPwAnswer;
        this.memberGender = memberGender;
        this.memberBirthDate = memberBirthDate;
        this.memberJoinDate = memberJoinDate;
    }

    public Member toSaveEntity(){
        return Member.builder()
                .memberId(memberId)
                .memberPw(memberPw)
                .memberName(memberName)
                .memberEmail(memberEmail)
                .memberEmailReceive(memberEmailReceive)
                .memberPwQuestion(memberPwQuestion)
                .memberPwAnswer(memberPwAnswer)
                .memberGender(memberGender)
                .memberBirthDate(memberBirthDate)
                .memberJoinDate(memberJoinDate)
                .build();
    }
}
