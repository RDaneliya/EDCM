package com.edcm.backend.infrastructure;

import com.edcm.backend.core.mappers.commodity.CommodityMapper;
import com.edcm.backend.core.properties.EddbProperties;
import com.edcm.backend.core.tools.EddbDataProvider;
import com.edcm.backend.core.tools.GithubDataProvider;
import com.edcm.backend.infrastructure.eddb.DefaultEddbDataProvider;
import com.edcm.backend.infrastructure.eddb.DefaultEddbOperations;
import com.edcm.backend.infrastructure.eddb.EddbOperations;
import com.edcm.backend.infrastructure.github.DefaultGithubDataProvider;
import com.edcm.backend.infrastructure.github.DefaultGithubOperations;
import com.edcm.backend.infrastructure.github.GithubOperations;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@RequiredArgsConstructor
public class InfrastructureConfig {
    @Value("${integrations.github-commodities-url}")
    private String githubCommoditiesUrl;

    private final EddbProperties eddbProperties;

    @Bean
    @Qualifier("githubCommoditiesWebClient")
    public WebClient githubWebClient() {
        return WebClient.create(githubCommoditiesUrl);
    }

    @Bean
    @Qualifier("eddbWebClient")
    public WebClient eddbWebClient() {
        var strategy = ExchangeStrategies.builder()
                                         .codecs(configurer -> configurer
                                                 .defaultCodecs()
                                                 .maxInMemorySize(20 * 1024 * 1024)
                                         )
                                         .build();
        return WebClient.builder()
                        .baseUrl(eddbProperties.getEddbUrl())
                        .exchangeStrategies(strategy)
                        .build();
    }

    @Bean
    public GithubOperations githubOperations(
            @Qualifier("githubCommoditiesWebClient") WebClient githubWebClient,
            ObjectMapper objectMapper
    ) {
        return new DefaultGithubOperations(githubWebClient, objectMapper);
    }

    @Bean
    public EddbOperations eddnOperations(
            @Qualifier("eddbWebClient") WebClient eddbWebClient,
            EddbProperties properties
    ) {
        return new DefaultEddbOperations(eddbWebClient, properties);
    }

    @Bean
    @Primary
    public GithubDataProvider githubDataProvider(GithubOperations githubOperations, CommodityMapper mapper) {
        return new DefaultGithubDataProvider(githubOperations, mapper);
    }

    @Bean
    @Primary
    public EddbDataProvider eddbDataProvider(EddbOperations eddbOperations) {
        return new DefaultEddbDataProvider(eddbOperations);
    }
}
