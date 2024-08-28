package com.dev.social_media_backend.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.social_media_backend.model.Comment;
import com.dev.social_media_backend.model.Post;
import com.dev.social_media_backend.model.User;
import com.dev.social_media_backend.repository.CommentRepository;
import com.dev.social_media_backend.repository.PostRepository;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostService postService;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserService userService;

    public Comment createComment(Comment comment, Integer postId, Integer userId) throws Exception {
        User user = userService.findUserById(userId);
        Post post = postService.findPostById(postId);

        comment.setUser(user);
        comment.setCreateAt(LocalDateTime.now());

        Comment savedComment = commentRepository.save(comment);
        post.getComments().add(savedComment);
        postRepository.save(post);

        return savedComment;
    }

    public Comment findCommentById(Integer commentId) throws Exception {
        Optional<Comment> opt = commentRepository.findById(commentId);

        if (opt.isEmpty()) {
            throw new Exception("comment not exist");
        }

        return opt.get();
    }

    public Comment likeComment(Integer commentTd, Integer userId) throws Exception {
        Comment comment = findCommentById(commentTd);
        User user = userService.findUserById(userId);

        if (!comment.getLiked().contains(user)) {
            comment.getLiked().add(user);
        } else {
            comment.getLiked().remove(user);
        }
        return commentRepository.save(comment);
    }
}
