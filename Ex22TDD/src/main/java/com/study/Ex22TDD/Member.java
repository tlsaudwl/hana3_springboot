
package com.study.Ex22TDD;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    private String loginId;
    private String loginPw;

}
