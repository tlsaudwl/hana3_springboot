package com.study.Pr02Calc_mine;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class Calculator {
    private int inputA;
    private int inputB;
    private int result;
    private double divResult;

    public void add(int a, int b) {
        inputA = a;
        inputB = b;
        result = a + b;
    }

    public void sub(int a, int b) {
        inputA = a;
        inputB = b;
        result = a - b;
    }

    public void mul(int a, int b) {
        inputA = a;
        inputB = b;
        result = a * b;
    }

    public void div(int a, int b) {
        inputA = a;
        inputB = b;
        divResult = (double) a / (double) b;
    }
}