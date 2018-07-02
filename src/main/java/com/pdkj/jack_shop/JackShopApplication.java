package com.pdkj.jack_shop;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableCaching
public class JackShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(JackShopApplication.class, args);
    }
}
