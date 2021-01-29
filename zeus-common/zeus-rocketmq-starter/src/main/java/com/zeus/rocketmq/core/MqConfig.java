package com.zeus.rocketmq.core;

public class MqConfig {

    private Class<?> messageClass;
    private boolean orderlyMessage;

    public Class<?> getMessageClass() {
        return messageClass;
    }

    public void setMessageClass(Class<?> messageClass) {
        this.messageClass = messageClass;
    }

    public boolean isOrderlyMessage() {
        return orderlyMessage;
    }

    public void setOrderlyMessage(boolean orderlyMessage) {
        this.orderlyMessage = orderlyMessage;
    }
}
