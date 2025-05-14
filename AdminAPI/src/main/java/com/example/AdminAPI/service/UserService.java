package com.example.AdminAPI.service;


import com.example.AdminAPI.config.SecurityConfig;
import com.example.AdminAPI.dto.LoginRequest;
import com.example.AdminAPI.dto.SignupRequest;
import com.example.AdminAPI.model.User;
import com.example.AdminAPI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void registerUser(SignupRequest request) {
        if(userRepository.findByEmail(request.email).isPresent()){
            throw new RuntimeException("Email is already registered");
        }

        User user = new User();
        user.setEmail(request.email);
        user.setUsername(request.username);
        user.setPassword(passwordEncoder.encode(request.password));

        userRepository.save(user);
    }

    public User loginUser(LoginRequest request) {
        User user = userRepository.findByEmail(request.email)
                .orElseThrow(() -> new RuntimeException("Not registered mail id"));

        if(!passwordEncoder.matches(request.password, user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }
        return user;
    }
}

