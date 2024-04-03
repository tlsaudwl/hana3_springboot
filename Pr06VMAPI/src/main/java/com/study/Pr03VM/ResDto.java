package com.study.Pr03VM;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResDto {
    String status; //"ok"
    String message; //"삭제되었습니다"
    int count; //1
}
