package net.zero123.crushspring.service;

import lombok.RequiredArgsConstructor;
import net.zero123.crushspring.dto.SessionSummary;
import net.zero123.crushspring.repository.SessionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final SessionRepository sessionRepository;

    @Transactional(readOnly = true)
    public List<SessionSummary> getSessions() {
        return sessionRepository.findAllByOrderByCreatedAtDesc().stream()
                .map(s -> new SessionSummary(s.getSessionId(), s.getPreview(), s.getCreatedAt()))
                .toList();
    }
}
