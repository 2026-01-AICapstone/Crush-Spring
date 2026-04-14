package net.zero123.crushspring.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.zero123.crushspring.dto.ChatRequest;
import net.zero123.crushspring.dto.ChatResponse;
import net.zero123.crushspring.service.ChatService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @PostMapping("/chat")
    public ChatResponse chat(@RequestBody @Valid ChatRequest request) {
        return chatService.chat(request);
    }
}
