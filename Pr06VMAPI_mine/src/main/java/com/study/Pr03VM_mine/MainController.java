package com.study.Pr03VM_mine;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController{
    final ProductRepository proRepo;

    public MainController(ProductRepository proRepo) {
        this.proRepo = proRepo;
    }


    @GetMapping("/")
    public String main(){
        return "productList";
    }

    @GetMapping("/addProductForm")
    public String addProductForm(){
        return "addProductForm";
    }

    @GetMapping("/editProductForm")
    public String editProductForm(@RequestParam int index, Model model){
        model.addAttribute("index", index);

        Product product = proRepo.list.get(index);
        model.addAttribute("product", product);

        return "editProductForm";
    }
}