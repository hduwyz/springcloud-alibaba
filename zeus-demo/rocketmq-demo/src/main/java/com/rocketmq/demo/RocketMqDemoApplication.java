package com.rocketmq.demo;

import com.zeus.rocketmq.annotation.EnableRocket;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRocket
public class RocketMqDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(RocketMqDemoApplication.class, args);
    }

}
