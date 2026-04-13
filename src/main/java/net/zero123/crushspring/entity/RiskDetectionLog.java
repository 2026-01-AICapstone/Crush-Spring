package net.zero123.crushspring.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "risk_detection_log")
@Getter
@NoArgsConstructor
public class RiskDetectionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conv_log_id", nullable = false, unique = true)
    private ConversationLog conversationLog;

    @Column(name = "risk_score")
    private Float riskScore;

    @Column(name = "detected_layer")
    private Integer detectedLayer;

    @Column(name = "intervention_triggered")
    private Boolean interventionTriggered;

    @Column(name = "intervention_type", length = 10)
    private String interventionType;

    @Column(name = "processing_time_ms")
    private Integer processingTimeMs;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Builder
    public RiskDetectionLog(ConversationLog conversationLog, Float riskScore,
                             Integer detectedLayer, Boolean interventionTriggered,
                             String interventionType, Integer processingTimeMs) {
        this.conversationLog = conversationLog;
        this.riskScore = riskScore;
        this.detectedLayer = detectedLayer;
        this.interventionTriggered = interventionTriggered;
        this.interventionType = interventionType;
        this.processingTimeMs = processingTimeMs;
        this.createdAt = LocalDateTime.now();
    }
}
