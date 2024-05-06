package com.example.demo.controller;

import com.example.demo.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


@RestController
public class AsyncController {

    @Autowired
    private AsyncService asyncService;


    @GetMapping("/greeting")
    public String great() {
        return "hello world!";
    }
    

    @GetMapping("/testpjm")
    public String process() throws InterruptedException, ExecutionException {


        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedNow = now.format(formatter);

        System.out.println("AsyncController start: " + Thread.currentThread().getName() + "/"+ formattedNow);
        CompletableFuture<String> future = asyncService.process();

          String sResult = "";
        sResult = future.get();
        now = LocalDateTime.now();
        formattedNow = now.format(formatter);
      
        System.out.println("AsyncController end: " + Thread.currentThread().getName() + "/"+ formattedNow);
       

        return sResult;
    }
}
