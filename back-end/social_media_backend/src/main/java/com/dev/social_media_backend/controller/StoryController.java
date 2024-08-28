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

import com.dev.social_media_backend.model.Story;
import com.dev.social_media_backend.model.User;
import com.dev.social_media_backend.service.StoryService;
import com.dev.social_media_backend.service.UserService;

@RestController
@RequestMapping("/api/story")
public class StoryController {
    @Autowired
    private StoryService storyService;
    @Autowired
    private UserService userService;

    @PostMapping
    public Story createStory(@RequestBody Story story, @RequestHeader("Authorization") String jwt) {
        User userRequest = userService.findUserByJwt(jwt);
        Story createdStory = storyService.createStory(story, userRequest);
        return createdStory;
    }

    @GetMapping("/user/{userId}")
    public List<Story> findUserStory(@PathVariable Integer userId) throws Exception {

        List<Story> stories = storyService.findStoryByUserId(userId);
        return stories;
    }
}
