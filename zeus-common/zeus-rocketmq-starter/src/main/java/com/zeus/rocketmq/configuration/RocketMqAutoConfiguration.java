package com.zeus.rocketmq.configuration;


import com.zeus.rocketmq.core.consumer.MethodInvoker;
import com.zeus.rocketmq.core.consumer.RocketMessageListenerContainer;
import com.zeus.rocketmq.core.producer.MqProducerContainer;
import com.zeus.rocketmq.extension.core.HookManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * 自动化配置类
 */


@EnableConfigurationProperties(RocketMqProperties.class)
public class RocketMqAutoConfiguration {

    @Autowired
    private RocketMqProperties rocketMqProperties;


    @Bean
    @ConditionalOnMissingBean(RocketMessageListenerContainer.class)
    public RocketMessageListenerContainer rocketMessageListenerContainer() {
        RocketMessageListenerContainer container = new RocketMessageListenerContainer();
        container.setNameSrvAddr(rocketMqProperties.getNameSrvAddr());
        container.setConsumeThreadMax(rocketMqProperties.getConsumeThreadMax());
        container.setConsumeThreadMin(rocketMqProperties.getConsumeThreadMin());
        return container;
    }


    @Bean
    @ConditionalOnMissingBean(HookManager.class)
    public HookManager hookManager() {
        return new HookManager();
    }

    @Bean
    @ConditionalOnMissingBean(MethodInvoker.class)
    public MethodInvoker methodInvoker() {
        return new MethodInvoker();
    }


    @Bean
    @ConditionalOnMissingBean(MqProducerContainer.class)
    public MqProducerContainer mqProducerContainer() {
        return new MqProducerContainer();
    }
}
