package com.dev.social_media_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.social_media_backend.dto.response.ApiResponse;
import com.dev.social_media_backend.model.Post;
import com.dev.social_media_backend.model.User;
import com.dev.social_media_backend.service.PostService;
import com.dev.social_media_backend.service.UserService;

@RestController
@RequestMapping("/api")
public class PostController {
    @Autowired
    PostService postService;
    @Autowired
    UserService userService;

    @PostMapping("/posts")
    public ResponseEntity<Post> createPost(@RequestBody Post post, @RequestHeader("Authorization") String jwt)
            throws Exception {
        User userRequest = userService.findUserByJwt(jwt);

        Post createdPost = postService.createNewPost(post, userRequest.getId());
        return new ResponseEntity<>(createdPost, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId,
            @RequestHeader("Authorization") String jwt)
            throws Exception {
        User userRequest = userService.findUserByJwt(jwt);
        String message = postService.deletePost(postId, userRequest.getId());
        ApiResponse res = new ApiResponse(message, true);
        return new ResponseEntity<ApiResponse>(res, HttpStatus.OK);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<Post> findPostByIdHandler(@PathVariable Integer postId) throws Exception {

        Post post = postService.findPostById(postId);
        return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);
    }

    @GetMapping("/posts/user/{userId}")
    public ResponseEntity<List<Post>> findUsersPost(@PathVariable Integer userId) {

        List<Post> posts = postService.findPostByUserId(userId);
        return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> findAllPost() {

        List<Post> posts = postService.findAllPost();
        return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
    }

    @PutMapping("/posts/save/{postId}")
    public ResponseEntity<Post> savePostHandler(@PathVariable Integer postId,
            @RequestHeader("Authorization") String jwt) throws Exception {
        User userRequest = userService.findUserByJwt(jwt);
        Post post = postService.savedPost(postId, userRequest.getId());
        return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);
    }

    @PutMapping("/posts/like/{postId}")
    public ResponseEntity<Post> likePostHandler(@PathVariable Integer postId,
            @RequestHeader("Authorization") String jwt)
            throws Exception {
        User userRequest = userService.findUserByJwt(jwt);
        Post post = postService.likePost(postId, userRequest.getId());
        return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);
    }
}
