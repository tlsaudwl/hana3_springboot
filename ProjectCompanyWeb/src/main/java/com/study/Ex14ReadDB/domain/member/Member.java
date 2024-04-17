package com.study.Ex14ReadDB.domain.member;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private Date memberBirthDate;
    @Column(name = "member_join_date", nullable = false)
    private LocalDate memberJoinDate = LocalDate.now();
}
