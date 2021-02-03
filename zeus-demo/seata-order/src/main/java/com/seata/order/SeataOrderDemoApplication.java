package com.seata.order;

import io.seata.spring.annotation.datasource.EnableAutoDataSourceProxy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableAutoDataSourceProxy
@EnableDiscoveryClient
public class SeataOrderDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeataOrderDemoApplication.class, args);
    }

}
