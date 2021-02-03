package com.seata.user;

import io.seata.spring.annotation.datasource.EnableAutoDataSourceProxy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoDataSourceProxy
public class SeataUserDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeataUserDemoApplication.class, args);
    }

}
