package net.zero123.crushspring.repository;

import net.zero123.crushspring.entity.ConversationLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConversationLogRepository extends JpaRepository<ConversationLog, Long> {

    List<ConversationLog> findBySession_SessionIdOrderByMessageOrderAsc(String sessionId);

    @Query("SELECT COALESCE(MAX(c.messageOrder), 0) FROM ConversationLog c WHERE c.session.sessionId = :sessionId")
    Integer findMaxMessageOrderBySessionId(@Param("sessionId") String sessionId);
}
