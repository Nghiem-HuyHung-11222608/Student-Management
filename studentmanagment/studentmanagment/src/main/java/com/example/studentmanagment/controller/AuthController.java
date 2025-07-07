package com.example.studentmanagment.controller;


import com.example.studentmanagment.dto.LoginRequest;
import com.example.studentmanagment.dto.LoginResponse;
import com.example.studentmanagment.model.User;
import com.example.studentmanagment.repository.UserRepository;
import com.example.studentmanagment.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public AuthController(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        Optional<User> userOpt = userRepository.findByEmail(request.getEmail());
        LoginResponse response = new LoginResponse();
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (user.getPassword().equals(request.getPassword())) {
                response.setSuccess(true);
                response.setMessage("Login successful");
                response.setUserId(user.getId());
                response.setName(user.getName());
                response.setEmail(user.getEmail());
                response.setToken(
                        jwtUtil.generateToken(
                                user.getId(),
                                user.getEmail(),
                                user.getRoles().stream().map(r -> r.getName()).collect(Collectors.toSet())
                        )
                );
                return ResponseEntity.ok(response);
            }
        }
        response.setSuccess(false);
        response.setMessage("Invalid email or password");
        return ResponseEntity.status(401).body(response);
    }
}