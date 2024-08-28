package com.dev.social_media_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.social_media_backend.model.Comment;
import com.dev.social_media_backend.model.User;
import com.dev.social_media_backend.service.CommentService;
import com.dev.social_media_backend.service.UserService;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;

    @PostMapping("/post/{postId}")
    public Comment createComment(@RequestBody Comment comment, @RequestHeader("Authorization") String jwt,
            @PathVariable Integer postId) throws Exception {
        User userRequest = userService.findUserByJwt(jwt);
        Comment createdComment = commentService.createComment(comment, postId, userRequest.getId());
        return createdComment;
    }

    @PutMapping("/like/{commentId}")
    public Comment likeComment(@RequestHeader("Authorization") String jwt,
            @PathVariable Integer commentId) throws Exception {
        User userRequest = userService.findUserByJwt(jwt);
        Comment likedComment = commentService.likeComment(commentId, userRequest.getId());
        return likedComment;
    }
}
