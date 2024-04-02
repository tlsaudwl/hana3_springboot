package com.study.Pr03VM_teacher;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {
    public static List<Product> list = new ArrayList<>();

    @GetMapping("/")
    public String main(Model model){
        model.addAttribute("list", list);
        return "productList";
    }

    @GetMapping("/addProductForm")
    public String addProductForm(){
        return "addProductForm";
    }

    // Form의 Input을 받는 방법
    // 1. @RequestParam 단일 매개변수
    // 2. @ModelAttribute 객체 매핑(바인딩)
    @PostMapping("/addProduct")
    @ResponseBody
    public String addProduct(
            // null을 허용한다
            @RequestParam(required = false, defaultValue = "기본값") String inputName,
            @RequestParam int inputPrice,
            @RequestParam LocalDate inputLimitDate){
        System.out.println(inputName);
        System.out.println(inputPrice);
        System.out.println(inputLimitDate);

        Product product = Product.builder()
                .name(inputName)
                .price(inputPrice)
                .limit_date(inputLimitDate)
                .build();
        list.add(product);

        System.out.println("size: " + list.size());

        return "<script>alert('상품이 추가되었습니다'); location.href='/'; <script/>";
        //return "redirect:/";  // 웹브라우저에게 응답을 주면서, 리다이렉트할 경로를 전달하면,
                              // 웹브라우저는 받자마자 이 경로로 다시 요청함
    }
}
