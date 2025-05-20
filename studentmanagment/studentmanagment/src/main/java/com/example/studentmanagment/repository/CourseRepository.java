package com.example.studentmanagment.repository;

import com.example.studentmanagment.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Course findByNameIgnoreCase(String name);
}