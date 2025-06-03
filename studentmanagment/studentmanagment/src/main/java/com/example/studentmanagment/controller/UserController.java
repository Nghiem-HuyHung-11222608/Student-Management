package com.example.studentmanagment.controller;

import com.example.studentmanagment.dto.UserDTO;
import com.example.studentmanagment.model.Course;
import com.example.studentmanagment.model.User;
import com.example.studentmanagment.service.UserService;
import com.example.studentmanagment.service.impl.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Create new user (admin only)
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody User user) {
        User saved = userService.createUser(user);
        return ResponseEntity.ok(UserServiceImpl.toDTO(saved));
    }

    // Get all users (admin only)
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers().stream()
                .map(UserServiceImpl::toDTO)
                .collect(Collectors.toList());
    }

    // Get user by id (admin only)
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        return UserServiceImpl.toDTO(userService.getUserById(id));
    }

    // Disable user (admin only)
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}/disable")
    public ResponseEntity<Void> disableUser(@PathVariable Long id) {
        userService.disableUser(id);
        return ResponseEntity.ok().build();
    }

    // Activate user (admin only)
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}/activate")
    public ResponseEntity<Void> activateUser(@PathVariable Long id) {
        userService.activateUser(id);
        return ResponseEntity.ok().build();
    }

    // Enroll in a course (student only)
    @PreAuthorize("hasRole('STUDENT')")
    @PostMapping("/{userId}/enroll/{courseId}")
    public ResponseEntity<Void> enrollCourse(@PathVariable Long userId, @PathVariable Long courseId) {
        userService.enrollCourse(userId, courseId);
        return ResponseEntity.ok().build();
    }

    // Unenroll from a course (student only)
    @PreAuthorize("hasRole('STUDENT')")
    @DeleteMapping("/{userId}/enroll/{courseId}")
    public ResponseEntity<Void> unenrollCourse(@PathVariable Long userId, @PathVariable Long courseId) {
        userService.unenrollCourse(userId, courseId);
        return ResponseEntity.ok().build();
    }

    // List enrolled courses
    @PreAuthorize("hasRole('STUDENT')")
    @GetMapping("/{userId}/courses")
    public ResponseEntity<Set<Course>> getEnrolledCourses(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getEnrolledCourses(userId));
    }
}