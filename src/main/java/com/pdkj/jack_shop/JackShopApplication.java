package com.pdkj.jack_shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableCaching
public class JackShopApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(JackShopApplication.class, args);
    }
    /**
     * 需要把web项目打成war包部署到外部tomcat运行时需要改变启动方式
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(JackShopApplication.class);
    }
}
