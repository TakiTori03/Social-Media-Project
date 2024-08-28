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

import com.dev.social_media_backend.model.Reels;
import com.dev.social_media_backend.model.User;
import com.dev.social_media_backend.service.ReelsService;
import com.dev.social_media_backend.service.UserService;

@RestController
@RequestMapping("/api/reels")
public class ReelsController {
    @Autowired
    private ReelsService reelsService;
    @Autowired
    private UserService userService;

    @PostMapping
    public Reels createReels(@RequestBody Reels reel, @RequestHeader("Authorization") String jwt) {
        User userRequest = userService.findUserByJwt(jwt);
        Reels createdReels = reelsService.createReel(reel, userRequest);
        return createdReels;
    }

    @GetMapping
    public List<Reels> findAllReels() {
        List<Reels> reels = reelsService.findAllReels();
        return reels;
    }

    @GetMapping("/user/{userId}")
    public List<Reels> findUsersReels(@PathVariable Integer userId) throws Exception {
        List<Reels> reels = reelsService.findUserReel(userId);
        return reels;
    }

}
