package com.study.Pr07LoginJoin;

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
