package com.study.Pr07LoginJoin_mine;

import lombok.*;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Member {
    private String username;
    private String password;
    private String email;
    private LocalDate joindate;
}

