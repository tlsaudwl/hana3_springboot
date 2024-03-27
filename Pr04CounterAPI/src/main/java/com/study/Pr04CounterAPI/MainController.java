package com.study.Pr04CounterAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @Autowired
    private Counter counter;

    @GetMapping("/")
    public String getCount() {
        return "count : " + counter.getCount();
    }

    @PostMapping("/plus")
    public String plusCount() {
        counter.setCount(counter.getCount() + 1);
        return "count : " + counter.getCount();
    }

    @PostMapping("/minus")
    public String minusCount() {
        counter.setCount(counter.getCount() - 1);
        return "count : " + counter.getCount();
    }
}