package com.pdkj.jack_shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class JackShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(JackShopApplication.class, args);
    }
}
