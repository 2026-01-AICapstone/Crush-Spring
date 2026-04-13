package net.zero123.crushspring.repository;

import net.zero123.crushspring.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SessionRepository extends JpaRepository<Session, String> {

    List<Session> findAllByOrderByCreatedAtDesc();
}
