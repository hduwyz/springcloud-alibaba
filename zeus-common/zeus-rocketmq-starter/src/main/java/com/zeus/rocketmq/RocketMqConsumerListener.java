package com.zeus.rocketmq;


import com.zeus.rocketmq.core.consumer.ConsumerConfig;
import com.zeus.rocketmq.core.consumer.MessageContext;
import com.zeus.rocketmq.exception.ConsumeException;

public interface RocketMqConsumerListener<E> {

    void onMessage(E message, MessageContext messageContext) throws ConsumeException;

    ConsumerConfig getConsumerConfig();


}
