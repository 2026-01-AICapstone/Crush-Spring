package net.zero123.crushspring.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "conversation_log")
@Getter
@NoArgsConstructor
public class ConversationLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id", nullable = false)
    private Session session;

    @Column(name = "message_order", nullable = false)
    private Integer messageOrder;

    @Column(name = "user_message", columnDefinition = "TEXT", nullable = false)
    private String userMessage;

    @Column(name = "ai_response", columnDefinition = "TEXT")
    private String aiResponse;

    @Column(name = "mode", length = 10, nullable = false)
    private String mode;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @OneToOne(mappedBy = "conversationLog", cascade = CascadeType.ALL, orphanRemoval = true)
    private RiskDetectionLog riskDetectionLog;

    @Builder
    public ConversationLog(Session session, Integer messageOrder, String userMessage,
                           String aiResponse, String mode) {
        this.session = session;
        this.messageOrder = messageOrder;
        this.userMessage = userMessage;
        this.aiResponse = aiResponse;
        this.mode = mode;
        this.createdAt = LocalDateTime.now();
    }
}
