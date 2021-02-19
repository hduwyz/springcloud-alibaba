package com.zeus.commons.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "zeus.common.core")
@Data
public class CommonProperties {
    //是否开启验证逻辑
    private Boolean enableVerify = Boolean.FALSE;
}
