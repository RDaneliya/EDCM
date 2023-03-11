package com.edcm.backend.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("integrations.github")
public class GithubProperties {
    private String baseUrl;
    private String commoditiesPath;
    private String rareCommoditiesPath;
}
