package io.michaeljgkopp.github.sb3springaiprompttoentity.response;

import io.michaeljgkopp.github.sb3springaiprompttoentity.entity.GeminiModel;

import java.util.List;

public record ModelListResponse(String object, List<GeminiModel> data) {
}
