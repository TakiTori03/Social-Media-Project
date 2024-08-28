package com.dev.social_media_backend.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.dev.social_media_backend.model.Post;
import com.dev.social_media_backend.model.User;
import com.dev.social_media_backend.repository.PostRepository;
import com.dev.social_media_backend.repository.UserRepository;

@Service
public class PostService {
    @Autowired
    PostRepository postRepository;

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    public Post createNewPost(Post post, Integer userId) throws Exception {
        User user = userService.findUserById(userId);

        Post newPost = Post.builder()
                .caption(post.getCaption())
                .image(post.getImage())
                .video(post.getVideo())
                .createAt(LocalDateTime.now())
                .user(user)
                .build();

        return postRepository.save(newPost);
    }

    public String deletePost(Integer portId, Integer userId) throws Exception {
        Post post = findPostById(portId);
        User user = userService.findUserById(userId);

        if (post.getUser().getId() != user.getId()) {
            throw new Exception("y can't delete this post");

        }
        postRepository.delete(post);

        return "Delete user successful";
    }

    public List<Post> findPostByUserId(Integer userId) {
        return postRepository.findPostByUserId(userId);
    }

    public Post findPostById(Integer postId) throws Exception {
        Optional<Post> opt = postRepository.findById(postId);
        if (opt.isEmpty()) {
            throw new Exception("post not found with id " + postId);
        }
        return opt.get();
    }

    public List<Post> findAllPost() {
        return postRepository.findAll();
    }

    public Post savedPost(Integer postId, Integer userId) throws Exception {
        Post post = findPostById(postId);
        User user = userService.findUserById(userId);
        if (user.getSavedPost().contains(post)) {
            user.getSavedPost().remove(post);
        } else {
            user.getSavedPost().add(post);
        }

        User updateUser = userRepository.save(user);

        return post;
    }

    public Post likePost(Integer postId, Integer userId) throws Exception {
        Post post = findPostById(postId);
        User user = userService.findUserById(userId);

        if (post.getLiked().contains(user)) {
            post.getLiked().remove(user);
        } else {
            post.getLiked().add(user);
        }

        return postRepository.save(post);
    }
}
