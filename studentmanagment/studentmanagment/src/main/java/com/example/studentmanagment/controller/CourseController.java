package com.example.studentmanagment.controller;

import com.example.studentmanagment.exception.ResourceNotFoundException;
import com.example.studentmanagment.model.Course;
import com.example.studentmanagment.model.Student;
import com.example.studentmanagment.repository.CourseRepository;
import com.example.studentmanagment.service.CourseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    // Create new course
    @PostMapping
    public Course createCourse(@RequestBody Course course) {
        return courseService.createCourse(course);
    }

    // Get all courses
    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    // Get course by id
    @GetMapping("/{id}")
    public Course getCourseById(@PathVariable Long id) {
        return courseService.getCourseById(id);
    }

    // Get all students enrolled in a course
    @GetMapping("/{id}/students")
    public Set<Student> getStudentsOfCourse(@PathVariable Long id) {
        return courseService.getStudentsOfCourse(id);
    }
    @GetMapping("/name/{name}")
    public Course getCourseByName(@PathVariable String name){
        return courseService.getByName(name);
        }

    @DeleteMapping("/{id}")
    public void deleteCourse (@PathVariable Long id){
        courseService.deleteCourse(id);
        }
    }
