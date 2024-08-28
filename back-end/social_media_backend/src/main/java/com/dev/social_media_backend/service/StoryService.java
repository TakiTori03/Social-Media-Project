package com.dev.social_media_backend.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.social_media_backend.model.Story;
import com.dev.social_media_backend.model.User;
import com.dev.social_media_backend.repository.StoryRepository;

@Service
public class StoryService {
    @Autowired
    private StoryRepository storyRepository;

    @Autowired
    private UserService userService;

    public Story createStory(Story story, User user) {
        Story createdStory = new Story();
        createdStory.setCaption(story.getCaption());
        createdStory.setImage(story.getImage());
        createdStory.setUser(user);
        createdStory.setTimestamp(LocalDateTime.now());

        return storyRepository.save(createdStory);
    }

    public List<Story> findStoryByUserId(Integer userId) throws Exception {
        User user = userService.findUserById(userId);
        return storyRepository.findByUserId(userId);
    }
}
