package net.zero123.crushspring.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ChatResponse(
        @JsonProperty("session_id") String sessionId,
        @JsonProperty("risk_score") float riskScore,
        @JsonProperty("risk_category") String riskCategory,
        @JsonProperty("detected_layer") Integer detectedLayer,
        @JsonProperty("intervention_triggered") boolean interventionTriggered,
        @JsonProperty("intervention_type") String interventionType,
        @JsonProperty("final_response") String finalResponse,
        @JsonProperty("processing_time_ms") int processingTimeMs
) {}
