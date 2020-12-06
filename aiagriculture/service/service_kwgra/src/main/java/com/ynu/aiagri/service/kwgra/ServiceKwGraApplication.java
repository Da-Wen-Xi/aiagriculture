package com.ynu.aiagri.service.kwgra;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

//@SpringBootApplication(exclude= DataSourceAutoConfiguration.class)
@ComponentScan({"com.ynu.aiagri.*"})
@SpringBootApplication
public class ServiceKwGraApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceKwGraApplication.class, args);
    }
}
