package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CompletableFuture;

@Service
public class AsyncService {

    @Autowired
    private RetryAsyncService1 retryAsyncService1;
    

    @Async
    public CompletableFuture<String> process() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedNow = now.format(formatter);

        System.out.println("AsyncService start: " + Thread.currentThread().getName() + "/"+ formattedNow);
        // 현재 시각을 문자열로 포맷팅

        try {
            Thread.sleep(5000);  // 모방된 지연
            retryAsyncService1.process("1");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

       
        now = LocalDateTime.now();
        System.out.println("AsyncService end: " +Thread.currentThread().getName() +":Processed"+"/"+now.format(formatter));
       
        return CompletableFuture.completedFuture("AsyncService end: " +Thread.currentThread().getName() +":Processed"+"/"+now.format(formatter));
    }
}
