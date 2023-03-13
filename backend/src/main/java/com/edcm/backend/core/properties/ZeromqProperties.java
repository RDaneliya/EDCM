package com.edcm.backend.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "zeromq")
public class ZeromqProperties {
    private String eddnChannelUrl;
    private long timeout;
}
