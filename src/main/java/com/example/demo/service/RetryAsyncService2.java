package com.example.demo.service;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class RetryAsyncService2 {
    
    @Async
    public void process(String step) {

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedNow = now.format(formatter);

        System.out.println("retry Service"+step+" start/: " + Thread.currentThread().getName() + "/"+ formattedNow);
        // 현재 시각을 문자열로  포맷팅  

        try {
            Thread.sleep(5000);  // 모방된 지연
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        now = LocalDateTime.now();
        System.out.println("retry Service"+step+" end: " + Thread.currentThread().getName()+ "/"+ now.format(formatter));

    }
}
