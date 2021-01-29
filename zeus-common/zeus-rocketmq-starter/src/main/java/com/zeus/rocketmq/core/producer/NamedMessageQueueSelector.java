package com.zeus.rocketmq.core.producer;

import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.springframework.beans.factory.BeanNameAware;

public interface NamedMessageQueueSelector extends MessageQueueSelector, BeanNameAware {

    String getBeanName();

}
