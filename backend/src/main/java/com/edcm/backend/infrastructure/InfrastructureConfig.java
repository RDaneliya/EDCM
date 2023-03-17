package com.edcm.backend.infrastructure;

import com.edcm.backend.core.mappers.CommodityMapper;
import com.edcm.backend.core.properties.EddbProperties;
import com.edcm.backend.core.properties.GithubProperties;
import com.edcm.backend.core.tools.EddbDataProvider;
import com.edcm.backend.core.tools.GithubDataProvider;
import com.edcm.backend.infrastructure.eddb.DefaultEddbDataProvider;
import com.edcm.backend.infrastructure.eddb.DefaultEddbOperations;
import com.edcm.backend.infrastructure.eddb.EddbOperations;
import com.edcm.backend.infrastructure.github.DefaultGithubDataProvider;
import com.edcm.backend.infrastructure.github.DefaultGithubOperations;
import com.edcm.backend.infrastructure.github.GithubOperations;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@RequiredArgsConstructor
public class InfrastructureConfig {

    private final EddbProperties eddbProperties;
    private final GithubProperties githubProperties;

    @Bean
    @Qualifier("githubCommoditiesWebClient")
    public WebClient githubWebClient() {
        return WebClient.create(githubProperties.getBaseUrl());
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
            CsvMapper csvMapper
    ) {
        return new DefaultGithubOperations(githubWebClient, githubProperties, csvMapper);
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
