package com.dev.social_media_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dev.social_media_backend.model.Chat;
import com.dev.social_media_backend.model.User;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Integer> {

    public List<Chat> findByUsers_Id(Integer userId);

    @Query("select c from Chat c Where :user Member of c.users And :reqUser Member of c.users")
    public Chat findChatByUsersId(@Param("user") User user, @Param("reqUser") User reqUser);
}
