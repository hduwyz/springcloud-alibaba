package com.websocket.demo.service.cglib;

import com.websocket.demo.service.jdk.LiuDeHua;
import com.websocket.demo.service.jdk.Star;
import com.websocket.demo.service.jdk.StarProxy;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxy implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("收钱");
        return methodProxy.invokeSuper(o, objects);
    }

    public Object createProxyObj(Class<?> clazz){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        return enhancer.create();
    }

    public static void main(String[] args) {
        CglibProxy starProxy = new CglibProxy();
        Star star1 = (Star) starProxy.createProxyObj(LiuDeHua.class);
        String result = star1.sing("刘德华");
        System.out.println(result);
    }
}
