package com.sensemore.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
public class SimpleTask {

    @Scheduled(cron = "0/5 * * * * ?")
    private void fixedExec(){
        System.out.println("执行时间:" + LocalDateTime.now());
    }
}
