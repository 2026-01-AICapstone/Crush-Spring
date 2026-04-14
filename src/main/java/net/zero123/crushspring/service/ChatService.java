package net.zero123.crushspring.service;

import lombok.RequiredArgsConstructor;
import net.zero123.crushspring.client.FastApiClient;
import net.zero123.crushspring.dto.ChatRequest;
import net.zero123.crushspring.dto.ChatResponse;
import net.zero123.crushspring.entity.ConversationLog;
import net.zero123.crushspring.entity.RiskDetectionLog;
import net.zero123.crushspring.entity.Session;
import net.zero123.crushspring.repository.ConversationLogRepository;
import net.zero123.crushspring.repository.RiskDetectionLogRepository;
import net.zero123.crushspring.repository.SessionRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final FastApiClient fastApiClient;
    private final SessionRepository sessionRepository;
    private final ConversationLogRepository conversationLogRepository;
    private final RiskDetectionLogRepository riskDetectionLogRepository;

    public ChatResponse chat(ChatRequest request) {
        // 세션 없으면 생성
        Session session = sessionRepository.findById(request.sessionId())
                .orElseGet(() -> sessionRepository.save(
                        Session.builder()
                                .sessionId(request.sessionId())
                                .preview(request.userMessage())
                                .build()
                ));

        // FastAPI 동기 호출 (block)
        ChatResponse aiResponse = fastApiClient.chat(request).block();

        // 비동기 DB 저장
        saveAsync(session, request, aiResponse);

        return aiResponse;
    }

    @Async
    @Transactional
    public void saveAsync(Session session, ChatRequest request, ChatResponse aiResponse) {
        int nextOrder = conversationLogRepository.findMaxMessageOrderBySessionId(session.getSessionId()) + 1;

        ConversationLog log = conversationLogRepository.save(
                ConversationLog.builder()
                        .session(session)
                        .messageOrder(nextOrder)
                        .userMessage(request.userMessage())
                        .aiResponse(aiResponse.finalResponse())
                        .mode(request.mode() == null ? "baseline" : request.mode())
                        .build()
        );

        riskDetectionLogRepository.save(
                RiskDetectionLog.builder()
                        .conversationLog(log)
                        .riskScore(aiResponse.riskScore())
                        .detectedLayer(aiResponse.detectedLayer())
                        .interventionTriggered(aiResponse.interventionTriggered())
                        .interventionType(aiResponse.interventionType())
                        .processingTimeMs(aiResponse.processingTimeMs())
                        .build()
        );
    }
}
