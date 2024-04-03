package com.study.Pr03VM_mine;

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
        model.addAttribute("count", list.size());
        return "productList";
    }

    @GetMapping("/addProductForm")
    public String addProductForm(){
        return "addProductForm";
    }

    @PostMapping("/addProduct")
    @ResponseBody
    public String addProduct(@RequestParam(required = false, defaultValue = "기본값") String inputName,
                             @RequestParam int inputPrice,
                             @RequestParam LocalDate inputLimitDate){
        Product product = Product.builder()
                .name(inputName)
                .price(inputPrice)
                .limit_date(inputLimitDate)
                .build();
        list.add(product);

        return "<script>alert('상품이 추가되었습니다'); location.href='/'; </script>";
    }

    @GetMapping("/deleteProduct")
    @ResponseBody
    public String deleteProduct(@RequestParam int index){
        list.remove(index);
        return "<script>alert('상품이 삭제되었습니다'); location.href='/'; </script>";
    }

    @GetMapping("/editProductForm")
    public String editProductForm(@RequestParam int index, Model model){
        Product product = list.get(index);
        model.addAttribute("product", product);
        model.addAttribute("index", index);
        return "editProductForm";
    }
    @PostMapping("/editProduct")
    @ResponseBody
    public String editProduct(@RequestParam int index,
                              @RequestParam String inputName,
                              @RequestParam int inputPrice,
                              @RequestParam LocalDate inputLimitDate){
        Product product = list.get(index);
        product.setName(inputName);
        product.setPrice(inputPrice);
        product.setLimit_date(inputLimitDate);

        list.set(index, product);
        return "<script>alert('상품이 수정되었습니다'); location.href='/'; </script>";

    }
}
