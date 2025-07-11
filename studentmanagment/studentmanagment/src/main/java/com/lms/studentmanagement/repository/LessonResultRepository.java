package com.lms.studentmanagement.repository;

import com.lms.studentmanagement.model.LessonResult;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface LessonResultRepository extends JpaRepository<LessonResult, Long> {
    Optional<LessonResult> findByLessonIdAndUserId(Long lessonId, Long userId);
}