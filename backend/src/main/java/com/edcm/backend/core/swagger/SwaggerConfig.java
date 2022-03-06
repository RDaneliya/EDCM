package com.edcm.backend.core.swagger;

import lombok.RequiredArgsConstructor;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi groupedOpenApi() {
        return GroupedOpenApi.builder()
            .group("api")
            .pathsToMatch("/api/**")
            .build();
    }
}
