package com.dev.social_media_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.social_media_backend.model.Message;

public interface MessageRepository extends JpaRepository<Message, Integer> {
    public List<Message> findByChatId(Integer chatId);
}
