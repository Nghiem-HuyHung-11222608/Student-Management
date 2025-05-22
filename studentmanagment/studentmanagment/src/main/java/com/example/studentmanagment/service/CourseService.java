package com.example.studentmanagment.service;


import com.example.studentmanagment.model.Course;
import com.example.studentmanagment.model.Student;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public  interface CourseService {
    Course createCourse(Course course);
    List<Course> getAllCourses();
    Course getCourseById(Long id);
    Set<Student> getStudentsOfCourse(Long id);
    Course getByName(String name);
    void deleteCourse(Long id);
}