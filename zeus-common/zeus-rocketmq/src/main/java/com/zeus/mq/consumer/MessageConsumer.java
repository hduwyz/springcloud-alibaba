package com.zeus.mq.consumer;


import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;

public class MessageConsumer {
    private static DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("ConsumerGroupName");
    private static int initialState = 0;

    public static DefaultMQPushConsumer getDefaultMQPushConsumer(){
        if(consumer == null){
            consumer = new DefaultMQPushConsumer("ConsumerGroupName");
        }

        if(initialState == 0){
            consumer.setNamesrvAddr("192.168.1.121:9876");
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
            initialState = 1;
        }

        return consumer;
    }
}
