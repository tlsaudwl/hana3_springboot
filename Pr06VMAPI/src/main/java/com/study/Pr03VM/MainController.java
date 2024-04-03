package com.study.Pr03VM;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {
    final ProductRepository pRepo;

    @GetMapping("/")
    public String main(){
        return "productList"; //productList.html로 응답함.
    }
    @GetMapping("/addProductForm")
    public String addProductForm(){
        return "addProductForm";
    }

    @GetMapping("/editProductForm")
    public String editProductForm(@RequestParam int index, Model model){
        model.addAttribute("index", index);

        Product product = pRepo.list.get(index);
        model.addAttribute("product", product);

        return "editProductForm";
    }
}
