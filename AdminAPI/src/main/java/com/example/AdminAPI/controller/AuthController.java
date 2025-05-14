package com.example.AdminAPI.controller;


import com.example.AdminAPI.dto.LoginRequest;
import com.example.AdminAPI.dto.SignupRequest;
import com.example.AdminAPI.dto.UserResponse;
import com.example.AdminAPI.repository.UserRepository;
import com.example.AdminAPI.model.User;
import com.example.AdminAPI.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody @Valid SignupRequest request) {
        try {
            userService.registerUser(request);
            return ResponseEntity.ok("User registered successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest request) {
        try {
            User user = userService.loginUser(request);
            return ResponseEntity.ok("Login successful for user: "+ user.getUsername());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(){
        return ResponseEntity.ok("Log out successfully");
    }

    @GetMapping("/user")
    public ResponseEntity<?> getUserByEmail(@RequestParam String email){
        Optional<User> userOptional = userService.findUserByEmail(email);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            return ResponseEntity.ok(new UserResponse(user.getUsername(), user.getEmail()));
        } else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found for email: "+email);
        }
    }
}
