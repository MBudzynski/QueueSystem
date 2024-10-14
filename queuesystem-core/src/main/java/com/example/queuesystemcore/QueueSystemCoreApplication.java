package com.example.queuesystemcore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class QueueSystemCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(QueueSystemCoreApplication.class, args);
    }
}
