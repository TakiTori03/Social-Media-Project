package com.dev.social_media_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.social_media_backend.model.Message;
import com.dev.social_media_backend.model.User;
import com.dev.social_media_backend.service.MessageService;
import com.dev.social_media_backend.service.UserService;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    @Autowired
    private MessageService messageService;
    @Autowired
    private UserService userService;

    @PostMapping("/chat/{chatId}")
    public Message createMessage(@RequestBody Message req, @RequestHeader("Authorization") String jwt,
            @PathVariable Integer chatId) throws Exception {
        User user = userService.findUserByJwt(jwt);

        Message message = messageService.createMessage(user, chatId, req);
        return message;
    }

    @GetMapping("/chat/{chatId}")
    public List<Message> findChatMessage(@RequestBody Message req, @RequestHeader("Authorization") String jwt,
            @PathVariable Integer chatId) throws Exception {
        User user = userService.findUserByJwt(jwt);

        List<Message> messages = messageService.findChatsMessages(chatId);
        return messages;
    }
}
