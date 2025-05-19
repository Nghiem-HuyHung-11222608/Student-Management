package com.example.studentmanagement.service;

import com.example.studentmanagement.model.Student;
import java.util.List;
import java.util.Optional;

public interface IStudentService {
    List<Student> getAllStudents();
    Optional<Student> getStudentById(Long id);
    Student saveStudent(Student student);
    void deleteStudent(Long id);
}