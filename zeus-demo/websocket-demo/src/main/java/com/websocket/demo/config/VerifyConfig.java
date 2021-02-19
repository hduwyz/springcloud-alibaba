package com.websocket.demo.config;

import com.zeus.core.handle.impl.EmptyVerifyHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Component
public class VerifyConfig {

    @Bean
    public EmptyVerifyHandler emptyVerifyHandler() {
        return new EmptyVerifyHandler();
    }
}
