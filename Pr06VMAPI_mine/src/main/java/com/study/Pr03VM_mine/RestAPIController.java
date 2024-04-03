package com.study.Pr03VM_mine;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class RestAPIController {
    final ProductRepository proRepo;

    public RestAPIController(ProductRepository proRepo){
        this.proRepo = proRepo;
    }

    @GetMapping("/products")
    public List<Product> products(){
        return proRepo.list;
    }

    @DeleteMapping("/deleteProduct")
    public ResDto deleteProduct(@RequestParam int index){
        proRepo.list.remove(index);

        ResDto dto = ResDto.builder()
                .status("ok")
                .message("삭제되었습니다")
                .count(1).build();

        return dto;
    }

    @PutMapping("/product")
    public ResDto editProduct(@RequestBody EditProductDto dto){
        int index = dto.getIndex();

        Product product = Product.builder()
                .name(dto.getInputName())
                .price(dto.getInputPrice())
                .limit_date(dto.getInputLimitDate())
                .build();
        proRepo.list.set(index, product);

        ResDto resDto = ResDto.builder()
                .status("ok")
                .message("수정되었습니다.")
                .count(1)
                .build();

        return resDto;
    }

    @PostMapping("/product")
    public ResDto addProduct(@RequestBody AddProductDto dto){
        Product product = Product.builder()
                .name(dto.getInputName())
                .price(dto.getInputPrice())
                .limit_date(dto.getInputLimitDate())
                .build();
        proRepo.list.add(product);

        ResDto resDto = ResDto.builder()
                .status("ok")
                .message("추가되었습니다")
                .count(1)
                .build();

        return resDto;
    }
}
