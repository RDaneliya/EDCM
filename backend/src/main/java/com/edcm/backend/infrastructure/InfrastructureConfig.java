package com.edcm.backend.infrastructure;

import com.edcm.backend.core.mappers.CommodityMapper;
import com.edcm.backend.core.tools.GithubDataProvider;
import com.edcm.backend.infrastructure.domain.github.DefaultGithubDataProvider;
import com.edcm.backend.infrastructure.domain.github.DefaultGithubOperations;
import com.edcm.backend.infrastructure.domain.github.GithubOperations;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@RequiredArgsConstructor
public class InfrastructureConfig {
    @Value("${github-commodities-url}")
    private String githubCommoditiesUrl;

    @Bean
    @Qualifier("githubCommoditiesWebClient")
    public WebClient webClient() {
        return WebClient.create(githubCommoditiesUrl);
    }

    @Bean
    public GithubOperations githubOperations(
        @Qualifier("githubCommoditiesWebClient") WebClient githubWebClient,
        ObjectMapper objectMapper) {
        return new DefaultGithubOperations(githubWebClient, objectMapper);
    }

    @Bean
    public GithubDataProvider githubDataProvider(GithubOperations githubOperations, CommodityMapper mapper) {
        return new DefaultGithubDataProvider(githubOperations, mapper);
    }
}
