package com.zeus.mq.config;

import com.zeus.rocketmq.core.producer.ProducerConfig;
import com.zeus.rocketmq.core.producer.RocketMqProducerTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RocketMqConfig {

    @Bean
    public RocketMqProducerTemplate getProduct(){
        RocketMqProducerTemplate rocketMqProducerTemplate = new RocketMqProducerTemplate();
        ProducerConfig producerConfig = new ProducerConfig();
        producerConfig.setProducerGroup("DEFAULT_GROUP");
        rocketMqProducerTemplate.setNamesrvAddr("192.168.1.121:9876");
        rocketMqProducerTemplate.setProducerConfig(producerConfig);
        return rocketMqProducerTemplate;
    }
}
