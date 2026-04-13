package net.zero123.crushspring.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "session")
@Getter
@NoArgsConstructor
public class Session {

    @Id
    @Column(name = "session_id", length = 36, nullable = false)
    private String sessionId;

    @Column(name = "preview", length = 20)
    private String preview;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("messageOrder ASC")
    private List<ConversationLog> conversationLogs = new ArrayList<>();

    @Builder
    public Session(String sessionId, String preview) {
        this.sessionId = sessionId;
        this.preview = preview;
        this.createdAt = LocalDateTime.now();
    }

    public void updatePreview(String firstMessage) {
        this.preview = firstMessage.length() > 20
                ? firstMessage.substring(0, 20)
                : firstMessage;
    }
}
