package com.study.Pr04CounterAPI;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Counter {
    private int count;

    public Counter(){
        this.count=0;
    }
}