package com.hbjs.hrsapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.hbjs")
@EnableCaching
public class HRSApplication {

    public static void main(String[] args) {
        SpringApplication.run(HRSApplication.class, args);
    }

}
