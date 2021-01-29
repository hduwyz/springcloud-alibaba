package com.zeus.rocketmq.core.producer;


import com.zeus.rocketmq.core.MqProducer;
import com.zeus.rocketmq.exception.ContatinerInitException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author jolly
 */
public class RocketMqProducerTemplate<M> implements MqProducer<M> {

    private static final Logger logger = LoggerFactory.getLogger(RocketMqProducerTemplate.class);


    private String namesrvAddr;

    private DefaultMQProducer defaultMQProducer;

    private ProducerConfig producerConfig;

    private AtomicBoolean started = new AtomicBoolean(false);


    public void setNamesrvAddr(String namesrvAddr) {
        this.namesrvAddr = namesrvAddr;
    }

    @Override
    public void start() throws MQClientException {
        if (started.get()) {
            throw new ContatinerInitException("this templeate is already init");
        }
        if (this.defaultMQProducer == null) {
            this.defaultMQProducer = new DefaultMQProducer();
        }
        this.defaultMQProducer.setProducerGroup(this.producerConfig.getProducerGroup());
        this.defaultMQProducer.setSendMsgTimeout(this.producerConfig.getTimeOut());
        this.defaultMQProducer.setNamesrvAddr(this.namesrvAddr);
        this.defaultMQProducer.start();
        this.started.set(true);
    }

    @Override
    public void shutdown() {
        if (started.get()) {
            this.defaultMQProducer.shutdown();
            started.set(false);
        }
    }


    @Override
    public void send(MessageProxy messageProxy) throws MQClientException, InterruptedException, RemotingException {
        SendCallback sendCallback = messageProxy.getSendCallback() == null ? new DefaultSendCallback() : messageProxy
                .getSendCallback();
        if (messageProxy.getMessage() == null) {
            throw new NullPointerException("消息不能为空");
        }
        if (this.producerConfig.isOrderlyMessage()) {
            MessageQueueSelector selector = messageProxy.getMessageQueueSelector();
            if (selector == null) {
                throw new NullPointerException("顺序消息必须配置MessageQueueSelector");
            }
            this.defaultMQProducer.send(messageProxy.getMessage(), selector, messageProxy.getSelectorArg(),
                    sendCallback);
        } else {
            this.defaultMQProducer.send(messageProxy.getMessage(), sendCallback);
        }
    }


    public ProducerConfig getProducerConfig() {
        return this.producerConfig;
    }

    public void setProducerConfig(ProducerConfig producerConfig) {
        this.producerConfig = producerConfig;
    }


    private static class DefaultSendCallback implements SendCallback {

        @Override
        public void onSuccess(SendResult sendResult) {

        }

        @Override
        public void onException(Throwable e) {

        }
    }
}
