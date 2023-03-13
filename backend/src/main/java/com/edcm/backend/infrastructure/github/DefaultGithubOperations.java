package com.edcm.backend.infrastructure.github;

import com.edcm.backend.core.properties.GithubProperties;
import com.edcm.backend.infrastructure.github.response.commodity.GithubCommodityItem;
import com.edcm.backend.infrastructure.github.response.commodity.GithubCommodityResponse;
import com.edcm.backend.infrastructure.github.response.economy.GithubEconomyItem;
import com.edcm.backend.infrastructure.github.response.economy.GithubEconomyResponse;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
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
        String response = webClient
                .get()
                .uri(githubProperties.getCommoditiesPath())
                .retrieve()
                .bodyToMono(String.class)
                .block();


        return instantiateCommodityResponse(response);
    }

    @Override
    public GithubCommodityResponse getRareCommodities() {
        String response = webClient
                .get()
                .uri(githubProperties.getRareCommoditiesPath())
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return instantiateCommodityResponse(response);
    }

    @Override
    public GithubEconomyResponse getEconomies() {
        String response = webClient
                .get()
                .uri(githubProperties.getEconomiesPath())
                .retrieve()
                .bodyToMono(String.class)
                .block();

        if (response != null && !response.isBlank()) {
            try {
                return parseCsv(GithubEconomyResponse.class, GithubEconomyItem.class, response);
            } catch (ReflectiveOperationException e) {
                log.error("Error instantiating class", e);
            }
        }
        return new GithubEconomyResponse();
    }

    private GithubCommodityResponse instantiateCommodityResponse(String responseString) {
        if (responseString != null && !responseString.isBlank()) {
            try {
                return parseCsv(GithubCommodityResponse.class, GithubCommodityItem.class, responseString);
            } catch (ReflectiveOperationException e) {
                log.error("Error instantiating class", e);
            }
        }
        return new GithubCommodityResponse();
    }

    private <T> T parseCsv(@NotNull Class<T> wrapper, @NotNull Class<?> item, @NotNull String response)
            throws ReflectiveOperationException {
        CsvSchema csvSchema = CsvSchema
                .emptySchema()
                .withHeader()
                .withColumnSeparator(',')
                .withoutQuoteChar();
        try (
                MappingIterator<GithubCommodityItem> iterator = csvMapper.readerFor(item)
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
