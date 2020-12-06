package com.ynu.aiagri.service.dev;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.ynu.aiagri.*"})
@MapperScan("com.ynu.aiagri.service.*.mapper")
public class ServiceDevApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceDevApplication.class, args);
    }
}
