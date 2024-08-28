package com.dev.social_media_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dev.social_media_backend.exception.UserException;
import com.dev.social_media_backend.model.User;
import com.dev.social_media_backend.service.UserService;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @GetMapping("/users/{userId}")
    public User getUserById(@PathVariable("userId") Integer id) throws UserException {
        return userService.findUserById(id);
    }

    @PutMapping("/users")
    public User updateUser(@RequestBody User user, @RequestHeader("Authorization") String jwt) throws UserException {
        User userRequest = userService.findUserByJwt(jwt);
        return userService.updateUser(user, userRequest.getId());
    }

    @DeleteMapping("/users/{userId}")
    public void deleteUser(@PathVariable("userId") Integer userId) throws Exception {
        userService.deleteUser(userId);
    }

    @PutMapping("/users/follow/{userId2}")
    public User followUserHandler(@RequestHeader("Authorization") String jwt, @PathVariable Integer userId2) {
        User userRequest = userService.findUserByJwt(jwt);
        User user = userService.followUser(userRequest.getId(), userId2);
        return user;
    }

    @GetMapping("/users/search")
    public List<User> searchUser(@RequestParam("query") String query) {
        List<User> users = userService.searchUser(query);
        return users;
    }

    @GetMapping("/users/profile")
    public User getUserFromToken(@RequestHeader("Authorization") String jwt) {
        User user = userService.findUserByJwt(jwt);
        return user;
    }

}
