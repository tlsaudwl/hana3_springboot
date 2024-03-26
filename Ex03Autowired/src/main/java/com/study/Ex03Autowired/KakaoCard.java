package com.study.Ex03Autowired;

import org.springframework.stereotype.Component;

@Component("kakaoCard")
public class KakaoCard implements ICard{
    @Override
    public void buy(String itemName) {
        System.out.println("KakaoCard : "+itemName);
    }
}
