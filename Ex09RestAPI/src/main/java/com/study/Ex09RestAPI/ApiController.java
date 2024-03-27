package com.study.Ex09RestAPI;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController // @Controller + @ResponseBody
// @ResponseBody : 모든 메소드에 @ReponseBody를 붙이는 효과
@RequestMapping("/api/v1")
public class ApiController {
    public static List<Member> memberList = new ArrayList<>();

    @PostMapping("/login") // URL : localhost:8080/api/v1/login
    public ResDto login(@RequestBody ReqDto reqDto){
        System.out.println(reqDto.getUsername());
        System.out.println(reqDto.getPassword());

        // 로그인 로직
        // reqDto.username이 DB(리스트)에 있는지 확인
        boolean found = false;
        for(Member member : memberList){
            if(member.getUsername().equals(reqDto.getUsername()) 
                    && member.getPassword().equals(reqDto.getPassword())){
                found = true; // 로그인 처리! 세션객체에 로그인 상태를 저장
                break;
            }
        }
        
        // {status: "ok", message: "로그인되었습니다"}
        ResDto resDto = new ResDto();
        if(found == true){
            resDto.setStatus("ok");
            resDto.setMessage("로그인 성공");
        } else{
            resDto.setStatus("fail");
            resDto.setMessage("로그인 실패");
        }
        
        return resDto; // 클래스객체가 JSON응답(문자열)으로 전송된다
    }

    @PostMapping("/join")
    public void join(){

    }
}
