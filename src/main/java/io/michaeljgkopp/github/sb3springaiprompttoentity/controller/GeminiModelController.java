package io.michaeljgkopp.github.sb3springaiprompttoentity.controller;

import io.michaeljgkopp.github.sb3springaiprompttoentity.entity.GeminiModel;
import io.michaeljgkopp.github.sb3springaiprompttoentity.response.ModelListResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.util.List;

@RestController
public class GeminiModelController {

    private static final Logger log = LoggerFactory.getLogger(GeminiModelController.class);
    @Value("${spring.ai.openai.api-key}")
    private String apiKey;
    @Value("${spring.ai.openai.chat.base-url}")
    private String baseUrl;
    @Value("${spring.ai.openai.models}")
    private String modelsUri;
    private final RestClient restClient;

    public GeminiModelController(RestClient.Builder builder) {
        this.restClient = builder
                .build();
    }

    @GetMapping("/models")
    public List<GeminiModel> models() {
        ResponseEntity<ModelListResponse> response = restClient.get()
                .uri(baseUrl + modelsUri)
                .header("Authorization", "Bearer " + apiKey)
                .retrieve()
                .toEntity(ModelListResponse.class);
        return response.getBody().data();
    }
}
