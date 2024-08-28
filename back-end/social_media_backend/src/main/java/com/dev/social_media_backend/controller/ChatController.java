package com.dev.social_media_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.social_media_backend.dto.request.ChatRequest;
import com.dev.social_media_backend.model.Chat;
import com.dev.social_media_backend.model.User;
import com.dev.social_media_backend.service.ChatService;
import com.dev.social_media_backend.service.UserService;

@RestController
@RequestMapping("/api/chats")
public class ChatController {
    @Autowired
    private ChatService chatService;
    @Autowired
    private UserService userService;

    @PostMapping
    public Chat createChat(@RequestHeader("Authorization") String jwt, @RequestBody ChatRequest request)
            throws Exception {
        User userRequest = userService.findUserByJwt(jwt);
        User user2 = userService.findUserById(request.getUserId());
        Chat chat = chatService.createChat(userRequest, user2);
        return chat;
    }

    @GetMapping
    public List<Chat> findUsersChat(@RequestHeader("Authorization") String jwt) {
        User userRequest = userService.findUserByJwt(jwt);

        List<Chat> chats = chatService.findUsersChat(userRequest.getId());

        return chats;
    }

}
