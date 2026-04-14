package net.zero123.crushspring.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.List;

public record ChatRequest(
        @NotBlank String sessionId,
        @NotBlank String userMessage,
        List<MessageDto> conversationHistory,
        @Pattern(regexp = "baseline|safety") String mode
) {
    public record MessageDto(String role, String content) {}
}
