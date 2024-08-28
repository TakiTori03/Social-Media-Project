package com.dev.social_media_backend.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    String chat_name;
    String chat_image;
    @ManyToMany
    List<User> users = new ArrayList<>();
    @OneToMany(mappedBy = "chat")
    List<Message> messages = new ArrayList<>();
    LocalDateTime timestamp;
}
