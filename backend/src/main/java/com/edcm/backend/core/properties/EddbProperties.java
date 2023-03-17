package com.edcm.backend.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "integrations.eddb")
public class EddbProperties {
    private String eddbUrl;
    private String factionsPath;
    private String commoditiesPath;
    private String governmentPath;
    private String allegiancesPath;
}
