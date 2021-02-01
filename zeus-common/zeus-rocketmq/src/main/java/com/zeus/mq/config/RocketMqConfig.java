package com.zeus.mq.config;

import com.zeus.rocketmq.configuration.RocketMqProperties;
import com.zeus.rocketmq.core.producer.ProducerConfig;
import com.zeus.rocketmq.core.producer.RocketMqProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RocketMqConfig {

    @Autowired
    private RocketMqProperties rocketMqProperties;

    @Bean
    public RocketMqProducerTemplate getProduct(){
        RocketMqProducerTemplate rocketMqProducerTemplate = new RocketMqProducerTemplate();
        ProducerConfig producerConfig = new ProducerConfig();
        producerConfig.setProducerGroup("DEFAULT_GROUP");
        rocketMqProducerTemplate.setNamesrvAddr(rocketMqProperties.getNameSrvAddr());
        rocketMqProducerTemplate.setProducerConfig(producerConfig);
        return rocketMqProducerTemplate;
    }
}
