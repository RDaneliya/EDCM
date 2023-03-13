package com.edcm.backend.infrastructure.eddb;

import com.edcm.backend.core.properties.EddbProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@RequiredArgsConstructor
public class DefaultEddbOperations implements EddbOperations {
    private final WebClient webClient;
    private final EddbProperties eddbProperties;

    @Override
    public EddbFactionsResponse getFactions() {
        var responseClass = new ParameterizedTypeReference<List<EddbFactionItem>>() {
        };
        List<EddbFactionItem> items = webClient.get()
                                               .uri(eddbProperties.getFactionsPath())
                                               .retrieve()
                                               .bodyToMono(responseClass)
                                               .block();
        return new EddbFactionsResponse(items);
    }
}
