package com.study.Ex14ReadDB.dto;

import com.study.Ex14ReadDB.entity.MemberEntity;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

//html입력폼 <-> DTO <-> DAO(Entity) <-> Repo클래스 <-> DB

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberSaveDto {
    private Long id;
    private String userId;
    private String userPw;
    private String userName;
    private String userRole;
    private String userAddress; //입력폼에 있고, DB에는 없는 열.
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate joinDate;

    //DTO를 save용 Entity로 변환해주는 메소드
    public MemberEntity toSaveEntity(){
        return MemberEntity.builder()
                .userId(userId)
                .userPw(userPw)
                .userName(userName)
                .userRole(userRole)
                .joinDate(joinDate)
                .build();
    }
    //DTO를 update용 Entity로 변환해주는 메소드
    public MemberEntity toUpdateEntity(){
        return MemberEntity.builder()
                .id(id)
                .userId(userId)
                .userPw(userPw)
                .userName(userName)
                .userRole(userRole)
                .joinDate(joinDate)
                .build();
    }
}