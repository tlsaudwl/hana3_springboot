
package com.study.Ex06Thymefleaf;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class MainController {
    @GetMapping("/")
    public String main(){
        //return "forward:index";
        return "redirect:index";
        //forward : 내부(세션,모델) 변수값을 가지고 페이지 이동( 내부 URL로 이동시 )
        //redirect : 내부 변수값을 버리고 페이지 이동( 타 사이트로 이동시 )
    }
    @GetMapping("/index")
    public String index(Model model){
        //Model 클래스 : 스프링에서 데이타를 담아서 전달하는 용도의 클래스 객체
        //서블릿 메소드에 매개변수로 선언하면, 스프링 컨테이너(컨텍스트)에서 자동 주입됨.
        model.addAttribute("name_text", "<ins>홍길동</ins>");
        model.addAttribute("name_html", "<ins>홍길동</ins>");
        model.addAttribute("server_value", "HONG");
        return "index"; //index.html 동적HTML로 응답함.
    }

    @GetMapping("/index2")
    public String index2(Model model){
        model.addAttribute("address", "한양");
        model.addAttribute("address_null", null);
        model.addAttribute("address_empty", "");
        return "index2";
    }

    @GetMapping("/index3")
    public String index3(Model model){
        model.addAttribute("standardDate", new Date());
        model.addAttribute("localDate", LocalDate.now() );
        model.addAttribute("localDateTime", LocalDateTime.now() );

        model.addAttribute( "number1", 12345678 );
        model.addAttribute( "number2", 123456.789 );

        return "index3"; //index3.html로 응답함.
    }

    @GetMapping("/index4")
    public String index4(Model model){
        model.addAttribute("role", "admin");

        return "index4"; //index4.html로 응답함.
    }

    @GetMapping("/index5")
    public String index5(Model model){
        Member member = new Member("hong", "1234");
        model.addAttribute("member", member);

        List<Member> list = new ArrayList<Member>();
        list.add( new Member("lee", "2222") );
        list.add( new Member("hana", "3333") );
        list.add( new Member("tom", "4444") );
        model.addAttribute("list", list);

        return "index5"; //index5.html로 응답함.
    }

    @GetMapping("/index6")
    public String index6() {
        return "index6"; //index6.html로 응답함.
    }
    @GetMapping("/index7")
    public String index7() {
        return "index7"; //index7.html로 응답함.
    }

    //FrontEnd - HTML에서 서버로 데이타를 전송하는 방법
    //HTML Form태그 : GET/POST 방식으로 submit한다.
    //JavaScript Fetch()함수 : GET/POST/PUT/DELETE 방식으로 전송한다.
    //JQuery Lib에서 ajax()함수 : GET/POST/PUT/DELETE 방식으로 전송한다.
    //Axios 모듈에서 axios()함수 : GET/POST/PUT/DELETE 방식으로 전송한다.
    //디버깅툴 : POSTMAN, THUNDER 등을 사용.

    //GET방식으로 요청 파라미터의 데이타를 받을 수 있다.
    //URI(URL) : localhost:8080/index-param?id=hong&pw=1234
    // id : hong
    // pw : 1234
    // @RequestParam : 요청 파라미터를 가져오는 어노테이션.
    @GetMapping("/index-param")
    public String indexParam(@RequestParam String id,
                             @RequestParam String pw,
                             Model model){
        System.out.println("id:" + id);
        System.out.println("pw:" + pw);
        model.addAttribute("id", id);
        model.addAttribute("pw", pw);
        return "index";
    }
    @PostMapping("/index-param")
    public String indexParamPost(@RequestParam String id,
                                 @RequestParam String pw,
                                 Model model){
        System.out.println("id:" + id);
        System.out.println("pw:" + pw);
        model.addAttribute("id", id);
        model.addAttribute("pw", pw);
        return "index";
    }
}
