package com.study.Ex09RestAPI;

import lombok.Data;

@Data
public class ResDto {
    // {status : "ok", message: "로그인되었습니다"}
    private String status; // 상태값
    private String message; // 메시지
}

// HtmlController(MainController) :  html 응답
// ApiController : JSON 응답