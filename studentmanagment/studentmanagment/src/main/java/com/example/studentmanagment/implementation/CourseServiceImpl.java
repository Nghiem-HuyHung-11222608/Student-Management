package com.example.studentmanagment.implementation;

import com.example.studentmanagment.exception.ResourceNotFoundException;
import com.example.studentmanagment.model.Course;
import com.example.studentmanagment.model.Student;
import com.example.studentmanagment.repository.CourseRepository;
import com.example.studentmanagment.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Course createCourse(Course course){
        return courseRepository.save(course);
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course getCourseById(Long id){
        return courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found: " + id));
    }

    @Override
    public Set<Student> getStudentsOfCourse(Long id){
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found: "+ id));
        return course.getStudents();
    }

    @Override
    public Course getByName(String name){
        Course course = courseRepository.findByNameIgnoreCase(name);
        if (course == null) {
            throw new ResourceNotFoundException("Course not found: " + name);
        }
        return course;
    }

    @Override
    public void deleteCourse(Long id){
        courseRepository.deleteById(id);
    }
}
