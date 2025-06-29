package com.lms.studentmanagement.service;

import com.lms.studentmanagement.dto.Exam.ExamAttemptDTO;
import com.lms.studentmanagement.dto.Exam.ExamDTO;
import com.lms.studentmanagement.dto.Exam.ExamResultDTO;

import java.util.List;

public interface ExamService {
    ExamDTO getExamForLesson(Long lessonId);
    ExamResultDTO submitExamAttempt(ExamAttemptDTO attemptDTO);
    int getTriesLeft(Long lessonId, Long userId);
}
