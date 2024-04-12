package com.study.Ex23Scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;

//스케줄러 : 주기적인 작업을 할때
//        예) 은행 정산 : 밤 11시 45분 ~ 밤 12시 (주기 : 1일)
//            생일 이벤트 : 내일 생일자에게 쿠폰 날리고 싶다.

@Slf4j
@Component
@EnableAsync //비동기적으로 처리하도록 제공하는 스프링 어노테이션
//각기 다른 쓰레드에서 실행된다.
public class Scheduler {
    @Autowired
    RestTemplate restTemplate;

    @Scheduled(fixedRate = 1000)
    public void task1() throws InterruptedException {
        log.info("FixedRat task - {}", System.currentTimeMillis()/1000);

        String url = "http://localhost:8080/api/batch-job";
        ResponseEntity response = restTemplate.getForEntity(url, String.class);
        System.out.println(response.getBody());

        log.info("dead!");
    }

//    @Scheduled(fixedRate = 1000)
//    public void task2() throws InterruptedException {
//        log.info("FixedRate task - {}", System.currentTimeMillis()/1000);
//        Thread.sleep( 5000 );
//        log.info("dead!");
//    }

//    @Async
//    @Scheduled(fixedRate = 1000*60*60*24, initialDelay = 5000)
//    public void task2() throws InterruptedException {
//        log.info("FixedRate task - {}", System.currentTimeMillis()/1000);
//        Thread.sleep( 5000 );
//        log.info("dead!");
//    }

    //Cron 표현식
    //초(0~59) 분(0~59) 시간(0~23) 일(1~31) 월(1~12) 요일(0~7)
    //Spring @Scheduled cron은 6자리 설정만 허용하며, 연도 설정은 할 수 없다.
    //               0초 15분 10시 15일 매달 알수없는요일
//    @Scheduled(cron = "0 15 10 15 * ?")
//    @Scheduled(cron = "* * * * * *")
//    @Scheduled(cron = "0 45 11 * * *")
//    public void task3() {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date now = new Date();
//        String strDate = sdf.format( now );
//        log.info("cron task - {}", strDate);
//    }
}