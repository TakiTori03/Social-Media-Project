package com.dev.social_media_backend.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.social_media_backend.model.Chat;
import com.dev.social_media_backend.model.Message;
import com.dev.social_media_backend.model.User;
import com.dev.social_media_backend.repository.ChatRepository;
import com.dev.social_media_backend.repository.MessageRepository;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ChatService chatService;

    @Autowired
    private ChatRepository chatRepository;

    public Message createMessage(User user, Integer chatId, Message req) throws Exception {

        Chat chat = chatService.findChatById(chatId);
        Message message = new Message();

        message.setChat(chat);
        message.setContent(req.getContent());
        message.setImage(req.getImage());
        message.setUser(user);
        message.setTimestamp(LocalDateTime.now());
        Message savedMessage = messageRepository.save(message);

        chat.getMessages().add(savedMessage);
        chatRepository.save(chat);

        return savedMessage;
    }

    public List<Message> findChatsMessages(Integer chatId) throws Exception {
        Chat chat = chatService.findChatById(chatId);
        return messageRepository.findByChatId(chatId);
    }
}
