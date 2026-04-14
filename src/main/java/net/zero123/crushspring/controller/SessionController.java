package net.zero123.crushspring.controller;

import lombok.RequiredArgsConstructor;
import net.zero123.crushspring.dto.SessionSummary;
import net.zero123.crushspring.service.SessionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SessionController {

    private final SessionService sessionService;

    @GetMapping("/sessions")
    public List<SessionSummary> getSessions() {
        return sessionService.getSessions();
    }
}
