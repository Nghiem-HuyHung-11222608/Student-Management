package com.example.studentmanagment.repository;

import com.example.studentmanagment.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
}