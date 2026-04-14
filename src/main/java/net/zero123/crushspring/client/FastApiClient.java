package net.zero123.crushspring.client;

import net.zero123.crushspring.dto.ChatRequest;
import net.zero123.crushspring.dto.ChatResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Component
public class FastApiClient {

    private final WebClient webClient;

    public FastApiClient(@Value("${fastapi.url}") String fastApiUrl) {
        this.webClient = WebClient.builder()
                .baseUrl(fastApiUrl)
                .build();
    }

    public Mono<ChatResponse> chat(ChatRequest request) {
        List<Map<String, String>> history = request.conversationHistory() == null ? List.of() :
                request.conversationHistory().stream()
                        .map(m -> Map.of("role", m.role(), "content", m.content()))
                        .toList();

        Map<String, Object> body = Map.of(
                "session_id", request.sessionId(),
                "user_message", request.userMessage(),
                "conversation_history", history,
                "mode", request.mode() == null ? "baseline" : request.mode()
        );

        return webClient.post()
                .uri("/ai/chat")
                .bodyValue(body)
                .retrieve()
                .bodyToMono(ChatResponse.class);
    }
}
