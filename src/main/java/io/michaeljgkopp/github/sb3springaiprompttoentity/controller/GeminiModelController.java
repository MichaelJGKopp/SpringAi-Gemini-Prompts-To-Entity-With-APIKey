package io.michaeljgkopp.github.sb3springaiprompttoentity.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.michaeljgkopp.github.sb3springaiprompttoentity.entity.GeminiModel;
import io.michaeljgkopp.github.sb3springaiprompttoentity.response.ModelListResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Optional;

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
        // make a GET request to the gemini models endpoint
        log.info("Getting models from {}", baseUrl + modelsUri);
        ResponseEntity<ModelListResponse> response = restClient.get()
                .uri(baseUrl + modelsUri)
                .header("Authorization", "Bearer " + apiKey)
                .retrieve()
                .toEntity(ModelListResponse.class);

        // models we want to return
        List<GeminiModel> models;

        // check for HTTP 2XX response
        log.info("Response status: {}", response.getStatusCode());
        if (response.getStatusCode().is2xxSuccessful()) {
            log.debug("Response: {}", response.getBody());

            // Extract models from the response
            models = Optional.ofNullable(response.getBody())
                    .map(ModelListResponse::data)
                    .orElseGet(() -> List.of()); // Empty list as fallback

            // Save response, list of models to a file
            try {
                // Convert models to JSON in pretty format
                ObjectMapper objectMapper = new ObjectMapper();
                String modelsJson = objectMapper.writerWithDefaultPrettyPrinter()
                        .writeValueAsString(models);
                // Write JSON to file
                Files.writeString(Path.of("models.json"), modelsJson,
                        StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            } catch (IOException e) {
                log.error("Error writing models to file", e);
            }

        } else {
            log.error("Error: {}", response.getStatusCode());
            throw new RuntimeException("Failed to get models");
        }

        return models;
    }
}
