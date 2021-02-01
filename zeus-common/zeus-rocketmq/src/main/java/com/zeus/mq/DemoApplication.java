package com.zeus.mq;

import com.alibaba.fastjson.JSONObject;
import com.zeus.rocketmq.annotation.EnableRocket;
import com.zeus.rocketmq.core.MqProducer;
import com.zeus.rocketmq.core.producer.MessageProxy;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

@SpringBootApplication
@EnableRocket
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
    @Slf4j
    @RestController
    static class TestController {

        @Autowired
        private MqProducer mqProducer;

        @GetMapping("/test")
        public String test() throws Exception{
            MessageProxy messageProxy = new MessageProxy();
            Message message = new Message();
            message.setBody("helloworld".getBytes(StandardCharsets.UTF_8));
            message.setTopic("MY_TOPIC1");
            message.setTags("TAG_1");
            message.setKeys("111");
            message.setWaitStoreMsgOK(true);
            messageProxy.setMessage(message);
            mqProducer.send(messageProxy);
            return "Invoke : ";
        }
    }
}
