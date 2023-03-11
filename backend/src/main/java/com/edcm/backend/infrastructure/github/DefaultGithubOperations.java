package com.edcm.backend.infrastructure.github;

import com.edcm.backend.core.properties.GithubProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.handler.MappedInterceptor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

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


        if (response != null && !response.isBlank()) {
            return parseCsv(response);
        }
        return null;
    }

    @Override
    public GithubCommodityResponse getRareCommodities() {
        String response = webClient
                .get()
                .uri(githubProperties.getRareCommoditiesPath())
                .retrieve()
                .bodyToMono(String.class)
                .block();


        if (response != null && !response.isBlank()) {
            return parseCsv(response);
        }
        return new GithubCommodityResponse(new ArrayList<>());
    }

    private GithubCommodityResponse parseCsv(@NotNull String response) {
        CsvSchema csvSchema = CsvSchema
                .emptySchema()
                .withHeader()
                .withColumnSeparator(',')
                .withoutQuoteChar();
        try (
                MappingIterator<GithubCommodityItem> iterator = csvMapper.readerFor(GithubCommodityItem.class)
                                                                         .with(csvSchema)
                                                                         .readValues(response)
        ) {
            return new GithubCommodityResponse(iterator.readAll());
        } catch (IOException e) {
            log.warn("Error downloading commodities", e);
        }
        return new GithubCommodityResponse(new ArrayList<>());
    }
}
