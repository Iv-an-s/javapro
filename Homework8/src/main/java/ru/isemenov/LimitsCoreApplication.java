package ru.isemenov;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class LimitsCoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(LimitsCoreApplication.class, args);
    }
}