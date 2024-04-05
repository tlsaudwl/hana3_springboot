package com.study.Pr07LoginJoin_mine;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginDto {
    private String inputName;
    private String inputPw;
}

