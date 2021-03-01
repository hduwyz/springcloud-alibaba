package com.websocket.demo.config;

import com.websocket.demo.service.DemoImportBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(DemoImportBean.class)
public class ImportBeanConfig {
}
