package com.example.studentmanagment.controller;

import com.example.studentmanagment.dto.CourseDTO;
import com.example.studentmanagment.dto.CourseMapper;
import com.example.studentmanagment.model.Course;
import com.example.studentmanagment.service.CourseService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
    private final CourseService courseService;
    public CourseController(CourseService courseService) { this.courseService = courseService; }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Course createCourse(@RequestBody Course course, @RequestParam Long adminId) {
        return courseService.createCourse(course, adminId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public Course updateCourse(@PathVariable Long id, @RequestBody Course course, @RequestParam Long adminId) {
        return courseService.updateCourse(id, course, adminId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable Long id, @RequestParam Long adminId) {
        courseService.deleteCourse(id, adminId);
    }

    @GetMapping("/{id}")
    public CourseDTO getCourseById(@PathVariable Long id) {
        return CourseMapper.toDTO(courseService.getCourseById(id));
    }

    @GetMapping
    public List<CourseDTO> getAllCourses() {
        return courseService.getAllCourses().stream()
                .map(CourseMapper::toDTO)
                .collect(Collectors.toList());
    }
}
