package com.zeus.mq.producer;


import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;

public class MessageProducer {
    private static DefaultMQProducer producer = new DefaultMQProducer("ProducerGroupName");
    private static int initialState = 0;

    public static DefaultMQProducer getDefaultMQProducer(){
        if(producer == null){
            producer = new DefaultMQProducer("ProducerGroupName");
        }

        if(initialState == 0){
            producer.setNamesrvAddr("192.168.1.121:9876");
            try {
                producer.start();
            } catch (MQClientException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return null;
            }

            initialState = 1;
        }

        return producer;
    }
}
