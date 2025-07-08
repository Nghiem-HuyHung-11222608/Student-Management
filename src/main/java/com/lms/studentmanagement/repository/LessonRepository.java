package com.lms.studentmanagement.repository;

import com.lms.studentmanagement.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
}