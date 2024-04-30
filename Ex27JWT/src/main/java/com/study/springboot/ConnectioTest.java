package com.study.springboot;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
class ConnectTest {
    private String message;

    public ConnectTest() {
        System.out.println("생성자 호출, message = " + message);
    }

    @PostConstruct
    public void init() {
        System.out.println("초기화 콜백");
        connect();
    }

    @PreDestroy
    public void close() {
        System.out.println("종료 전 콜백");
        disconnect();
    }

    public void connect() {
        System.out.println("연결 완료 - message : " + message);
    }

    public void disconnect() {
        System.out.println("연결 종료");
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
