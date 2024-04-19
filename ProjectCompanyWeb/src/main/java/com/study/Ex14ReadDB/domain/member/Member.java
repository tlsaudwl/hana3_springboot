package com.study.Ex14ReadDB.domain.member;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "company_member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_idx", nullable = false)
    private Long memberIdx;
    @Column(name = "member_id", nullable = false)
    private String memberId;
    @Column(name = "member_pw", nullable = false)
    private String memberPw;
    @Column(name = "member_name", nullable = false)
    private String memberName;
    @Column(name = "member_email", nullable = false)
    private String memberEmail;
    @Column(name = "member_email_receive", nullable = false)
    private int memberEmailReceive;
    @Column(name = "member_pw_question", nullable = false)
    private int memberPwQuestion;
    @Column(name = "member_pw_answer", nullable = false)
    private String memberPwAnswer;
    @Column(name = "member_gender", nullable = false)
    private String memberGender;
    @Column(name = "member_birth_date", nullable = false)
    private LocalDate memberBirthDate;
    @Column(name = "member_join_date", nullable = false)
    private LocalDate memberJoinDate = LocalDate.now();

    @Builder
    public Member(String memberId, String memberPw, String memberName, String memberEmail,
                  int memberEmailReceive, int memberPwQuestion, String memberPwAnswer, String memberGender,
                  LocalDate memberBirthDate, LocalDate memberJoinDate){
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
}
