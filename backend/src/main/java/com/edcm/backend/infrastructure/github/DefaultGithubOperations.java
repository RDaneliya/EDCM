package com.edcm.backend.infrastructure.github;

import com.edcm.backend.core.properties.GithubProperties;
import com.edcm.backend.infrastructure.github.response.allegiance.GithubAllegianceResponse;
import com.edcm.backend.infrastructure.github.response.commodity.GithubCommodityResponse;
import com.edcm.backend.infrastructure.github.response.economy.GithubEconomyResponse;
import com.edcm.backend.infrastructure.github.response.government.GithubGovernmentResponse;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultGithubOperations implements GithubOperations {

    @Qualifier("githubCommoditiesWebClient")
    private final WebClient webClient;
    private final GithubProperties githubProperties;

    private final CsvMapper csvMapper;

    @Override
    public GithubCommodityResponse getRegularCommodities() {
        String response = getCsv(githubProperties.getCommoditiesPath());

        return instantiateCommodityResponse(response);
    }

    @Override
    public GithubCommodityResponse getRareCommodities() {
        String response = getCsv(githubProperties.getRareCommoditiesPath());

        return instantiateCommodityResponse(response);
    }

    @Override
    public GithubEconomyResponse getEconomies() {
        String response = getCsv(githubProperties.getEconomiesPath());

        if (response != null && !response.isBlank()) {
            try {
                return parseCsv(GithubEconomyResponse.class, GithubEconomyResponse.GithubEconomyItem.class, response);
            } catch (ReflectiveOperationException e) {
                log.error("Error instantiating GithubEconomyResponse class", e);
            }
        }
        return new GithubEconomyResponse();
    }

    @Override
    public GithubGovernmentResponse getGovernments() {
        String response = getCsv(githubProperties.getGovernmentPath());

        try {
            return parseCsv(
                    GithubGovernmentResponse.class,
                    GithubGovernmentResponse.GovernmentResponseItem.class,
                    response
            );
        } catch (ReflectiveOperationException e) {
            log.error("Error instantiating GithubGovernmentResponse class", e);
        }
        return new GithubGovernmentResponse();
    }

    @Override
    public GithubAllegianceResponse getAllegiances() {
        String response = getCsv(githubProperties.getAllegiancesPath());

        if (response != null && !response.isBlank()) {
            try {
                return parseCsv(
                        GithubAllegianceResponse.class,
                        GithubAllegianceResponse.AllegianceResponseItem.class,
                        response
                );
            } catch (ReflectiveOperationException e) {
                log.error("Error instantiating GithubEconomyResponse class", e);
            }
        }
        return new GithubAllegianceResponse();
    }

    private GithubCommodityResponse instantiateCommodityResponse(String responseString) {
        if (responseString != null && !responseString.isBlank()) {
            try {
                return parseCsv(
                        GithubCommodityResponse.class,
                        GithubCommodityResponse.GithubCommodityItem.class,
                        responseString
                );
            } catch (ReflectiveOperationException e) {
                log.error("Error instantiating class", e);
            }
        }
        return new GithubCommodityResponse();
    }

    @Nullable
    private String getCsv(String path) {
        return webClient
                .get()
                .uri(path)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    private <T> T parseCsv(@NotNull Class<T> wrapper, @NotNull Class<?> item, @NotNull String response)
            throws ReflectiveOperationException {
        CsvSchema csvSchema = CsvSchema
                .emptySchema()
                .withHeader()
                .withColumnSeparator(',')
                .withoutQuoteChar();
        try (
                MappingIterator<GithubCommodityResponse.GithubCommodityItem> iterator = csvMapper.readerFor(item)
                                                                                                 .with(csvSchema)
                                                                                                 .readValues(response)
        ) {
            return wrapper.getDeclaredConstructor(List.class).newInstance(iterator.readAll());
        } catch (IOException e) {
            log.warn("Error parsing response", e);
        }
        return wrapper.getDeclaredConstructor(List.class).newInstance(new ArrayList<>());
    }
}
