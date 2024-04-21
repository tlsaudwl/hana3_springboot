package com.study.Ex14ReadDB.dto;

import com.study.Ex14ReadDB.domain.customerqna.CustomerQna;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerQnaDto {
    private Long qnaIdx;
    private String qnaName;
    private String qnaPw;
    private String qnaTitle;
    private String qnaContent;
    private LocalDate qnaDate;

    public CustomerQnaDto(CustomerQna customerQna) {
        this.qnaIdx = customerQna.getQnaIdx();
        this.qnaName = customerQna.getQnaName();
        this.qnaPw = customerQna.getQnaPw();
        this.qnaTitle = customerQna.getQnaTitle();
        this.qnaContent = customerQna.getQnaContent();
        this.qnaDate = customerQna.getQnaDate();
    }
}
