package net.zero123.crushspring.dto;

import java.time.LocalDateTime;

public record SessionSummary(
        String sessionId,
        String preview,
        LocalDateTime createdAt
) {}
