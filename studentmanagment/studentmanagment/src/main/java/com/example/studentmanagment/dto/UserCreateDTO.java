package com.example.studentmanagment.dto;

import lombok.Data;
import java.util.Set;

@Data
public class UserCreateDTO {
    private String name;
    private String email;
    private String password;
    private String address;
    private String phoneNumber;
    private Set<String> roles; // Accepts role names: ["ADMIN", "STUDENT"]
}