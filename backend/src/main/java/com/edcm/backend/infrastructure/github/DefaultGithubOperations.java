package com.edcm.backend.infrastructure.github;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultGithubOperations implements GithubOperations {

    @Qualifier("githubCommoditiesWebClient")
    private final WebClient webClient;
    private final ObjectMapper objectMapper;

    @Override
    public GithubCommodityResponse getCommodities() {
        String response = webClient
                .get()
                .retrieve()
                .bodyToMono(String.class)
                .block();

        if (response != null) {

            try {
                MapType type = objectMapper
                        .getTypeFactory()
                        .constructMapType(HashMap.class, String.class, GithubCommodityItem.class);
                return new GithubCommodityResponse(objectMapper.readValue(response, type));
            } catch (JsonProcessingException e) {
                log.error("Failed to get commodities", e);
                return null;
            }
        }
        return null;
    }
}
