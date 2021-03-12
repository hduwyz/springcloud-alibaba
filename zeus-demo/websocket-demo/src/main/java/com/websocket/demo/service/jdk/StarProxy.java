package com.websocket.demo.service.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class StarProxy implements InvocationHandler {
    private Object target;

    public void setTarget(Object target){
        this.target = target;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("收钱");
        Object result = method.invoke(target, args);
        return result;
    }

    public Object createProxyObj(){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),
                this);
    }


    public static void main(String[] args) {
        Star star = new LiuDeHua();
        StarProxy starProxy = new StarProxy();
        starProxy.setTarget(star);
        Star star1 = (Star) starProxy.createProxyObj();
        String result = star1.sing("刘德华");
        System.out.println(result);
    }

}
