package com.example.easydonatemaster;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EasyDonateMasterApplication {

    public static void main(String[] args) {
        SpringApplication.run(EasyDonateMasterApplication.class, args);
    }

}
