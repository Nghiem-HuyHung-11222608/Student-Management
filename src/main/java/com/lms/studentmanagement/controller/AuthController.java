package com.lms.studentmanagement.controller;


import com.lms.studentmanagement.dto.LoginRequest;
import com.lms.studentmanagement.dto.LoginResponse;
import com.lms.studentmanagement.model.Role;
import com.lms.studentmanagement.model.User;
import com.lms.studentmanagement.repository.UserRepository;
import com.lms.studentmanagement.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        Optional<User> userOpt = userRepository.findByEmail(request.getEmail());

        var invalidResponse = LoginResponse.builder()
                .success(false)
                .message("Invalid email or password")
                .build();

        if (userOpt.isEmpty()) {
            return ResponseEntity.status(401).body(invalidResponse);
        }

        User user = userOpt.get();
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return ResponseEntity.status(401).body(invalidResponse);
        }

        var userId = user.getId();
        var email = user.getEmail();
        var response = LoginResponse.builder()
                .success(true)
                .message("Login successful")
                .userId(userId)
                .name(user.getName())
                .email(email)
                .token(jwtUtil.generateToken(
                        userId, email,
                        user.getRoles().stream()
                                .map(Role::getName)
                                .collect(Collectors.toSet())
                ))
                .build();

        return ResponseEntity.ok(response);
    }
}