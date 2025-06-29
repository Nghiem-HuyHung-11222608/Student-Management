package com.lms.studentmanagement.repository.Exam;

import com.lms.studentmanagement.model.Exam.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamRepository extends JpaRepository<Exam, Long> {
    Exam findByLessonId(Long lessonId);
}
