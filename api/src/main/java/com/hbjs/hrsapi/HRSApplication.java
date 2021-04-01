package com.hbjs.hrsapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.hbjs")
public class HRSApplication {

    public static void main(String[] args) {
        SpringApplication.run(HRSApplication.class, args);
    }

}