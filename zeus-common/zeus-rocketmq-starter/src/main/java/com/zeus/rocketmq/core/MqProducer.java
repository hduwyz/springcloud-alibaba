package com.zeus.rocketmq.core;

import com.zeus.rocketmq.core.producer.MessageProxy;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.remoting.exception.RemotingException;

public interface MqProducer<M> {

    void send(MessageProxy<M> messageProxy) throws MQClientException, InterruptedException, RemotingException;


    void start() throws MQClientException;

    void shutdown();
}
