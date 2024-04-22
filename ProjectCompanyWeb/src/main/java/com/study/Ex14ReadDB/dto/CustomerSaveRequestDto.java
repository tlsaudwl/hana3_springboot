package com.study.Ex14ReadDB.dto;

import com.study.Ex14ReadDB.domain.customer.Customer;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class CustomerSaveRequestDto {
    private String one2oneName;
    private String one2onePhone;
    private String one2oneEmail;
    private String one2oneAddress;
    private String one2oneTitle;
    private String one2oneContent;
    private LocalDate one2oneDate = LocalDate.now();

    public CustomerSaveRequestDto(String one2oneName, String one2onePhone, String one2oneEmail, String one2oneAddress,
                                  String one2oneTitle, String one2oneContent, LocalDate one2oneDate) {
        this.one2oneName = one2oneName;
        this.one2onePhone = one2onePhone;
        this.one2oneEmail = one2oneEmail;
        this.one2oneAddress = one2oneAddress;
        this.one2oneTitle = one2oneTitle;
        this.one2oneContent = one2oneContent;
        this.one2oneDate = one2oneDate;
    }

    public Customer toSaveEntity() {
        return Customer.builder()
                .one2oneName(one2oneName)
                .one2onePhone(one2onePhone)
                .one2oneEmail(one2oneEmail)
                .one2oneAddress(one2oneAddress)
                .one2oneTitle(one2oneTitle)
                .one2oneContent(one2oneContent)
                .one2oneDate(LocalDate.now())
                .build();
    }
}
