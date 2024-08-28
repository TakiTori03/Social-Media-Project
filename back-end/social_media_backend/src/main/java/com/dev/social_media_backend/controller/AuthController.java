package com.dev.social_media_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.social_media_backend.config.JwtProvider;
import com.dev.social_media_backend.dto.request.LoginRequest;
import com.dev.social_media_backend.dto.response.AuthResponse;
import com.dev.social_media_backend.model.User;
import com.dev.social_media_backend.repository.UserRepository;
import com.dev.social_media_backend.service.CustomerUserDetailService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    CustomerUserDetailService customerUserDetailService;

    @PostMapping("/signup")
    public AuthResponse createUser(@RequestBody User user) throws Exception {
        User isExist = userRepository.findByEmail(user.getEmail());
        if (isExist != null) {
            throw new Exception("this email already used with another account");
        }
        User newUser = User.builder()
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .password(passwordEncoder.encode(user.getPassword()))
                .gender(user.getGender())
                .build();

        User savedUser = userRepository.save(newUser);
        Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(),
                savedUser.getPassword());
        String token = JwtProvider.generateToken(authentication);
        return new AuthResponse(token, "Register success");
    }

    @PostMapping("/signin")
    public AuthResponse signin(@RequestBody LoginRequest request) {
        Authentication authentication = authenticate(request.getEmail(), request.getPassword());
        String token = JwtProvider.generateToken(authentication);
        return new AuthResponse(token, "Login success");
    }

    private Authentication authenticate(String email, String password) {
        UserDetails userDetails = customerUserDetailService.loadUserByUsername(email);

        if (userDetails == null) {
            throw new BadCredentialsException("invalid username");

        }
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("password not matched");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
