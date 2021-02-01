package com.rocketmq.demo.listener;

import com.zeus.rocketmq.annotation.RocketListeners;
import com.zeus.rocketmq.annotation.RocketMQListener;
import org.springframework.stereotype.Component;

@Component
@RocketListeners(topic = "MY_TOPIC1")
public class MyListener {

    @RocketMQListener(messageClass = String.class,tag = "TAG_1")
    public void method1(String message){
        System.out.println(message);
    }

    @RocketMQListener(messageClass = Object.class,tag = "TAG_2")
    public void method2(Object message){
        System.out.println(message.toString());
    }
}
