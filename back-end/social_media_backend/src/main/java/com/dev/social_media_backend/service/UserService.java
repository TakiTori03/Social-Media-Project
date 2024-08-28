package com.dev.social_media_backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.social_media_backend.config.JwtProvider;
import com.dev.social_media_backend.exception.UserException;
import com.dev.social_media_backend.model.Post;
import com.dev.social_media_backend.model.User;
import com.dev.social_media_backend.repository.UserRepository;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    // @Autowired
    // PostService postService;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User registerUser(User user) {
        User newUser = User.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();

        return userRepository.save(newUser);
    }

    public User findUserById(Integer userId) throws UserException {
        Optional<User> user = userRepository.findById(userId);

        if (user.isPresent()) {
            return user.get();
        }
        throw new UserException("user not exist with userId" + userId);
    }

    public User updateUser(User user, Integer userId) throws UserException {
        Optional<User> user1 = userRepository.findById(userId);

        if (user1.isEmpty()) {
            throw new UserException("user not exist" + userId);
        }

        User oldUser = user1.get();

        if (user.getFirstName() != null) {
            oldUser.setFirstName(user.getFirstName());
        }
        if (user.getLastName() != null) {
            oldUser.setLastName(user.getLastName());
        }
        if (user.getEmail() != null) {
            oldUser.setEmail(user.getEmail());
        }
        if (user.getGender() != null) {
            oldUser.setGender(user.getGender());
        }

        User updateUser = userRepository.save(oldUser);
        return updateUser;

    }

    public void deleteUser(Integer userId) throws Exception {
        Optional<User> user1 = userRepository.findById(userId);

        if (user1.isEmpty()) {
            throw new Exception("user not exist" + userId);
        }
        userRepository.delete(user1.get());

    }

    public User findUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return user;
    }

    public User followUser(Integer userRequestId, Integer userId2) {
        User userRequest = userRepository.findUserById(userRequestId);
        User user2 = userRepository.findUserById(userId2);
        userRequest.getFollowers().add(userRequest.getId());
        user2.getFollowings().add(user2.getId());

        userRepository.save(userRequest);
        userRepository.save(user2);
        return userRequest;
    }

    public List<User> searchUser(String query) {
        return userRepository.searchUser(query);
    }

    public User findUserByJwt(String jwt) {
        String email = JwtProvider.getEmaiFromJwtToken(jwt);

        User user = userRepository.findByEmail(email);
        return user;

    }
}
