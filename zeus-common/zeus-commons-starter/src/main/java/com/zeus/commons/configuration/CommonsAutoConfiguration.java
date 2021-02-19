package com.zeus.commons.configuration;

import com.zeus.core.handle.VerifyHandler;
import com.zeus.core.handle.VerifyHandlerChain;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@Slf4j
@EnableConfigurationProperties(CommonProperties.class)
public class CommonsAutoConfiguration {

    @Bean
    @ConditionalOnBean(VerifyHandler.class)
    public VerifyHandlerChain verifyHandlerChain() {
        log.info("开始创建verifyHandlerChain=====================");
        VerifyHandlerChain verifyHandlerChain = new VerifyHandlerChain();
        return verifyHandlerChain;
    }
}
