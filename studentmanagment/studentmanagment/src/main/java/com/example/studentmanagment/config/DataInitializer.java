package com.example.studentmanagment.config;


import com.example.studentmanagment.model.Role;
import com.example.studentmanagment.model.User;
import com.example.studentmanagment.repository.RoleRepository;
import com.example.studentmanagment.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Date;

@Component
public class DataInitializer {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public DataInitializer(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init() {
        // Ensure all roles exist
        for (String roleName : new String[]{"ADMIN", "TEACHER", "STUDENT"}) {
            if (roleRepository.findByName(roleName) == null) {
                Role r = new Role();
                r.setName(roleName);
                roleRepository.save(r);
            }
        }


        String adminEmail = "admin@mail.com";
        if (userRepository.findByEmail(adminEmail).isEmpty()) {
            Role adminRole = roleRepository.findByName("ADMIN");
            User admin = new User();
            admin.setName("Admin");
            admin.setEmail(adminEmail);
            admin.setPassword("adminpass");
            admin.setAddress("System");
            admin.setPhoneNumber("0000000000");
            admin.setCreatedAt(new Date());
            admin.setActive(true);
            admin.setRoles(Collections.singleton(adminRole));
            userRepository.save(admin);
        }
    }
}