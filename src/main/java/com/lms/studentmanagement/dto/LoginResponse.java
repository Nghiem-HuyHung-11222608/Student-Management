package com.lms.studentmanagement.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {
    private boolean success;
    private String message;
    private Long userId;
    private String name;
    private String email;
    private String token; // JWT token
}
