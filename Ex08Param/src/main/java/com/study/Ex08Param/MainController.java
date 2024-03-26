package com.study.Ex08Param;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {
    @RequestMapping("/")
    public String main(){
        return "redirect:loginForm";
    }
    //Form 요청(UI가 있음) : loginForm, joinForm, orderForm, findPWForm
    //Action 요청(UI없이 처리로직) : loginAction, joinAction, ...
    @RequestMapping("/loginForm")
    public String loginForm(){
        return "login_form"; //login_form.html로 응답
    }

    //input태그 value를 파라미터로 받을 수 있다.
    @RequestMapping("/loginAction1")
    public String loginAction1(HttpServletRequest request,
                               Model model,
                               //매개변수를 주입된 Member객체에 매핑해준다.
                               Member member){
        //sumbit으로 받는 모든 입력값을 한 클래스로 설계한다.
        //매개변수 자동매핑을 통해서 한 클래스 객체로 주입받는다.
        System.out.println( member.getUsername() );
        //model.addAttribute("member", member);
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        model.addAttribute("username", username);
        model.addAttribute("password", password);
        return "loginResult"; //loginResult.html로 응답
    }

    //요청URL : localhost:8080/loginAction1/hong/1234
    //@PathVariable : 호출 경로를 매개변수로 받을 수 있음.
    @RequestMapping("/loginAction1/{username}/{password}")
    public String loginAction1( @PathVariable("username") String username,
                                @PathVariable("password") String password,
                                Model model,
                                //매개변수를 주입된 Member객체에 매핑해준다.
                                Member member){

        System.out.println(member);
        //"변사또"
        //System.out.println( member0.getUsername() );
        //"hong" :
        System.out.println( member.getUsername() );

        model.addAttribute("username", username);
        model.addAttribute("password", password);
        return "loginResult"; //loginResult.html로 응답
    }

    @RequestMapping("/loginAction2")
    public String loginAction2(
            //required = false : 매개변수로 null 허용
            @RequestParam(value="username",
                    required = false, defaultValue = "김진사") String username,
            @RequestParam("password") String password,
            Model model){
        model.addAttribute("username", username);
        model.addAttribute("password", password);
        return "loginResult";
    }

    // login_form.html 변경해줘야함 loginAction4로
    @RequestMapping("/loginAction4")
    public String loginAction5(
            @RequestParam Map<String, Object> map,
            Model model){
        //map : "username" : "hong"
        //    : "password" : "1234"
        System.out.println(map.get("username"));

        model.addAttribute("map", map);

        return "loginResult3";
    }
}