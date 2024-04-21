package com.study.Ex14ReadDB.domain.customerqna;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "company_qna")
public class CustomerQna {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qna_idx", nullable = false)
    private Long qnaIdx;
    @Column(name = "qna_name", nullable = false)
    private String qnaName;
    @Column(name = "qna_pw", nullable = false)
    private String qnaPw;
    @Column(name = "qna_title", nullable = false)
    private String qnaTitle;
    @Column(name = "qna_content", nullable = false)
    private String qnaContent;
    @Column(name = "qna_date", nullable = false)
    private LocalDate qnaDate = LocalDate.now();

    @Builder

    public CustomerQna(String qnaName, String qnaPw, String qnaTitle, String qnaContent, LocalDate qnaDate) {
        this.qnaName = qnaName;
        this.qnaPw = qnaPw;
        this.qnaTitle = qnaTitle;
        this.qnaContent = qnaContent;
        this.qnaDate = qnaDate;
    }
}


