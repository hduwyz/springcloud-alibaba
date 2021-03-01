package com.websocket.demo;

import com.websocket.demo.service.DemoImportBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class WebsocketDemoApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(WebsocketDemoApplication.class, args);
        DemoImportBean demoImportBean = applicationContext.getBean(DemoImportBean.class);
        demoImportBean.sayHello();

    }

}
