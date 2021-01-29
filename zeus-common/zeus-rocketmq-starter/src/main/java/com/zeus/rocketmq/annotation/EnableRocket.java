package com.zeus.rocketmq.annotation;

import com.zeus.rocketmq.configuration.RocketMqAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 使用此注解开启rocketMQ
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(RocketMqAutoConfiguration.class)
public @interface EnableRocket {
}
