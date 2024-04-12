package com.study.Pr03VM_teacher.controller;

import com.study.Pr03VM_teacher.dao.IProductDao;
import com.study.Pr03VM_teacher.dto.ProductDto;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
public class MainController {
    final IProductDao productDao;

    public MainController(IProductDao productDao) {
        this.productDao = productDao;
    }

    @GetMapping("/")
    public String main(Model model){
        List<ProductDto> productList = productDao.list();
        model.addAttribute("list", productList);
        model.addAttribute("count", productList.size());
        return "productList";
    }
    @GetMapping("/addProductForm")
    public String addProductForm(){
        return "addProductForm";
    }

    @PostMapping("/addProduct")
    @ResponseBody
    public String addProduct(
            @RequestParam(required = false, defaultValue = "기본값") String inputName,
            @RequestParam int inputPrice,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate inputLimitDate){
        System.out.println(inputName);
        System.out.println(inputPrice);
        System.out.println(inputLimitDate);

        ProductDto product = ProductDto.builder()
                .name(inputName)
                .price(inputPrice)
                .limit_date(inputLimitDate)
                .build();
        productDao.insertProduct( product );

        return "<script>alert('상품이 추가되었습니다.'); location.href='/';</script>";
    }

    // deleteProduct?index=0
    @GetMapping("/deleteProduct")
    @ResponseBody
    public String deleteProduct(@RequestParam int index){

        int res = productDao.deleteProduct( index );
        System.out.println(res);
        return "<script>alert('상품이 삭제되었습니다.'); location.href='/';</script>";
    }
    // editProductForm?index=0
    @GetMapping("/editProductForm")
    public String editProductForm(@RequestParam int index, Model model){
        Optional<ProductDto> productOptional = productDao.getProductById(index);
        if(productOptional.isPresent()){
            ProductDto product = productOptional.get();
            model.addAttribute("product", product);
            model.addAttribute("index", index);
            return "editProductForm";
        } else {
            return "errorPage";
        }
    }
    //editProduct
    @PostMapping("/editProduct")
    @ResponseBody
    public String editProduct(@RequestParam int index,
                              @RequestParam String inputName,
                              @RequestParam int inputPrice,
                              @RequestParam LocalDate inputLimitDate ){
        Optional<ProductDto> productOptional = productDao.getProductById(index);
        if(productOptional.isPresent()){
            ProductDto product = productOptional.get();
            product.setName( inputName );
            product.setPrice( inputPrice );
            product.setLimit_date( inputLimitDate );
            productDao.updateProduct(product, index);
            return "<script>alert('상품이 수정되었습니다.'); location.href='/';</script>";
        } else{
            return "<script>alert('상품이 존재하지 않습니다.'); location.href='/';</script>";
        }
    }
}