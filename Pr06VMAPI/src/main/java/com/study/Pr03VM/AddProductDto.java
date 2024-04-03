package com.study.Pr03VM;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AddProductDto {
    private String inputName;
    private int inputPrice;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate inputLimitDate;
}
