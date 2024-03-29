package com.study.Pr01Counter;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Calculator {
    double result = 0;

    public double calc(ReqDto reqDto){
        int intNum1 = Integer.parseInt(reqDto.getNum1());
        int intNum2 = Integer.parseInt(reqDto.getNum2());

        if(reqDto.getOperation().equals("add")){
            this.result = this.add(intNum1, intNum2);
        }else if(reqDto.getOperation().equals("sub")){
            this.result = this.sub(intNum1, intNum2);
        }else if(reqDto.getOperation().equals("mul")){
            this.result = this.mul(intNum1, intNum2);
        }else{
            this.result = this.div(intNum1, intNum2);
        }
        return this.result;
    }

    public int add(int a, int b) {
        result = a + b;
        return (int)result;
    }

    public int sub(int a, int b) {
        result = a - b;
        return (int)result;
    }

    public int mul(int a, int b) {
        result = a * b;
        return (int)result;
    }

    public double div(int a, int b) {
        result = (double) a / (double) b;
        return result;
    }
}
