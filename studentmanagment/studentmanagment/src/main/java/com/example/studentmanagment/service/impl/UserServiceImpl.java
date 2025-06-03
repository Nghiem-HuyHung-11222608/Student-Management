package com.example.studentmanagment.service.impl;

import com.example.studentmanagment.dto.UserDTO;
import com.example.studentmanagment.model.Course;
import com.example.studentmanagment.model.Role;
import com.example.studentmanagment.model.User;
import com.example.studentmanagment.repository.CourseRepository;
import com.example.studentmanagment.repository.UserRepository;
import com.example.studentmanagment.repository.RoleRepository;
import com.example.studentmanagment.service.UserService;
import com.example.studentmanagment.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, CourseRepository courseRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    @Transactional
    public User createUser(User user) {
        user.setActive(true);
        if (user.getCreatedAt() == null) user.setCreatedAt(new java.util.Date());
        User saved = userRepository.save(user);
        logger.info("Created user: {}", saved.getEmail());
        return saved;
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + id));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void disableUser(Long id) {
        User user = getUserById(id);
        user.setActive(false);
        userRepository.save(user);
        logger.info("Disabled user: {}", user.getEmail());
    }



    @Override
    @Transactional
    public void activateUser(Long id) {
        User user = getUserById(id);
        user.setActive(true);
        userRepository.save(user);
        logger.info("Activated user: {}", user.getEmail());
    }

    @Override
    @Transactional
    public void enrollCourse(Long userId, Long courseId) {
        User user = getUserById(userId);
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found: " + courseId));
        user.getEnrolledCourses().add(course);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void unenrollCourse(Long userId, Long courseId) {
        User user = getUserById(userId);
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found: " + courseId));
        user.getEnrolledCourses().remove(course);
        userRepository.save(user);
    }

    @Override
    public Set<Course> getEnrolledCourses(Long userId) {
        User user = getUserById(userId);
        return user.getEnrolledCourses();
    }


    public static UserDTO toDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setAddress(user.getAddress());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setCreatedAt(user.getCreatedAt());
        dto.setActive(user.isActive());
        if (user.getRoles() != null) {
            dto.setRoles(user.getRoles().stream().map(Role::getName).collect(Collectors.toSet()));
        }
        return dto;
    }
}