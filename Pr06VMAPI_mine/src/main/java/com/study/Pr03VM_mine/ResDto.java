package com.study.Pr03VM_mine;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResDto {
    String status;
    String message;
    int count;
}
