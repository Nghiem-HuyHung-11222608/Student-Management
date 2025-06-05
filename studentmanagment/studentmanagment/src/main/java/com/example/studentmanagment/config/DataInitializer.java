package com.example.studentmanagment.config;

import com.example.studentmanagment.model.Role;
import com.example.studentmanagment.repository.RoleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    private final RoleRepository roleRepository;

    public DataInitializer(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void init() {
        for (String roleName : new String[]{"ADMIN", "TEACHER", "STUDENT"}) {
            if (roleRepository.findByName(roleName) == null) {
                Role r = new Role();
                r.setName(roleName);
                roleRepository.save(r);
            }
        }
    }
}
