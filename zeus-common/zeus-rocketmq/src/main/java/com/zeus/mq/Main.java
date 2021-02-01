package com.zeus.mq;

import com.zeus.mq.consumer.MessageConsumer;
import com.zeus.mq.producer.MessageProducer;
import com.zeus.rocketmq.core.producer.MessageProxy;
import com.zeus.rocketmq.core.producer.RocketMqProducerTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.List;

@Slf4j
public class Main {

    public static void main(String[] args){
        sendMsg();
//        receiveMsg();
    }

    public static void sendMsg(){

        // 获取消息生产者
        DefaultMQProducer producer = MessageProducer.getDefaultMQProducer();

        try {
            for(int i=0;i<2000;i++){
                if (i%2==0){
                    Message msg = new Message(
                            "MY_TOPIC",                   // topic
                            "TAG_1",                         // tag
                            "OrderID00"+i,                  // key
                            ("Hello MetaQ"+i + "TAG_1").getBytes());  // body
                    producer.setVipChannelEnabled(false);
                    SendResult sendResult = producer.send(msg);
                    log.info("sendResult:{}", sendResult);
                }else{
                    Message msg = new Message(
                            "MY_TOPIC",                   // topic
                            "TAG_2",                         // tag
                            "OrderID00"+i,                  // key
                            ("Hello MetaQ"+i + "TAG_2").getBytes());  // body
                    SendResult sendResult = producer.send(msg);
                    log.info("sendResult:{}", sendResult);
                }
            }
        } catch (MQClientException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (RemotingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MQBrokerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        producer.shutdown();
    }

    public static void receiveMsg(){

        // 获取消息生产者
        DefaultMQPushConsumer consumer = MessageConsumer.getDefaultMQPushConsumer();

        // 订阅主体
        try {
            consumer.subscribe("MY_TOPIC", "*");

            consumer.registerMessageListener(new MessageListenerConcurrently() {

                /**
                 * * 默认msgs里唯独一条消息，能够通过设置consumeMessageBatchMaxSize參数来批量接收消息
                 */
                public ConsumeConcurrentlyStatus consumeMessage(
                        List<MessageExt> msgs, ConsumeConcurrentlyContext context) {

                    log.info("currentThreadName:{} and Receive New Messages:{}",Thread.currentThread().getName(),msgs);

                    MessageExt msg = msgs.get(0);

                    if (msg.getTopic().equals("MY_TOPIC")) {
                        // 运行TopicTest1的消费逻辑
                        if (msg.getTags() != null && msg.getTags().equals("TAG_1")) {
                            // 运行TagA的消费
                            log.info("MsgBody:{}",new String(msg.getBody()));
                        } else if (msg.getTags() != null
                                && msg.getTags().equals("TAG_2")) {
                            // 运行TagC的消费
                        } else if (msg.getTags() != null
                                && msg.getTags().equals("TAG_3")) {
                            // 运行TagD的消费
                        }
                    } else if (msg.getTopic().equals("TopicTest2")) {
                        // 运行TopicTest2的消费逻辑
                    }

                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
            });

            /**
             * Consumer对象在使用之前必须要调用start初始化。初始化一次就可以<br>
             */
            consumer.start();

            log.info("Consumer Started.");
        } catch (MQClientException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
