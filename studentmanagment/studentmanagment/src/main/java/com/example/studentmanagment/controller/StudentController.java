package com.example.studentmanagment.controller;

import com.example.studentmanagment.model.Student;
import com.example.studentmanagment.model.Course;
//import com.example.studentmanagment.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

//@RestController
//RequestMapping("/api/students")
//public class StudentController {
    /* private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    //Add new student
    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    //Get all students
    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    //Get student by id
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    //Update student
    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student updated) {
        return studentService.updateStudent(id, updated);
    }

    //Delete student
    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }


    //Search students by name
    @GetMapping("/search")
    public List<Student> searchStudentsByName(@RequestParam String name) {
        return studentService.searchStudentsByName(name);
    }

    //Enroll courses
    @PostMapping("/{id}/enroll")
    public Student enrollCourses(@PathVariable Long id, @RequestBody Set<Long> courseIds) {
        return studentService.enrollCourses(id, courseIds);
    }

    //List courses of student
    @GetMapping("/{id}/courses")
    public Set<Course> getCoursesOfStudent(@PathVariable Long id) {
        return studentService.getCoursesOfStudent(id);
    }
}*/