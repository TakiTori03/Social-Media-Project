package com.dev.social_media_backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.social_media_backend.model.Reels;
import com.dev.social_media_backend.model.User;
import com.dev.social_media_backend.repository.ReelsRepository;

@Service
public class ReelsService {

    @Autowired
    private ReelsRepository reelsRepository;
    @Autowired
    private UserService userService;

    public Reels createReel(Reels reel, User user) {
        Reels createReel = new Reels();
        createReel.setTitle(reel.getTitle());
        createReel.setUser(user);
        createReel.setVideo(reel.getVideo());
        return reelsRepository.save(createReel);
    }

    public List<Reels> findAllReels() {
        return reelsRepository.findAll();
    }

    public List<Reels> findUserReel(Integer userId) throws Exception {
        userService.findUserById(userId);
        return reelsRepository.findByUserId(userId);
    }
}
