package com.study.Pr01Counter;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ApiController {
    final Calculator calculator;

    @PostMapping("/calc")
    public ResDto calc(@RequestBody ReqDto reqDto){
        System.out.println(reqDto.getOperation());
        System.out.println(reqDto.getNum1());
        System.out.println(reqDto.getNum2());

        double result = calculator.calc(reqDto);
        ResDto resDto = new ResDto();
        resDto.setStatus("ok");
        resDto.setResult(result);

        return resDto;
    }
}
