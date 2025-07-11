package com.lms.studentmanagement.service.impl;

import com.lms.studentmanagement.client.exam.ExamClient;
import com.lms.studentmanagement.dto.LessonResultDTO;
import com.lms.studentmanagement.dto.exam.response.ExamDto;
import com.lms.studentmanagement.dto.exam.response.ExamResultDto;
import com.lms.studentmanagement.exception.ResourceNotFoundException;
import com.lms.studentmanagement.model.Lesson;
import com.lms.studentmanagement.model.LessonResult;
import com.lms.studentmanagement.model.User;
import com.lms.studentmanagement.repository.LessonRepository;
import com.lms.studentmanagement.repository.LessonResultRepository;
import com.lms.studentmanagement.repository.UserRepository;
import com.lms.studentmanagement.service.LessonResultService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LessonResultServiceImpl implements LessonResultService {
    private static final Logger logger = LoggerFactory.getLogger(LessonResultServiceImpl.class);

    private final LessonResultRepository lessonResultRepository;
    private final LessonRepository lessonRepository;
    private final UserRepository userRepository;
    private final ExamClient examClient;

    @Override
    @Transactional
    public LessonResult submitLessonResult(Long lessonId, LessonResult result, Long studentId) {
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new ResourceNotFoundException("Lesson not found: " + lessonId));
        User student = userRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found: " + studentId));
        result.setLesson(lesson);
        result.setUser(student);
        try {
            if ("fail".equals(result.getUploadedFile())) throw new RuntimeException("File save failed!");
            logger.info("Lesson result submitted for lesson: {} by student: {}", lessonId, studentId);
            return lessonResultRepository.save(result);
        } catch (Exception e) {
            logger.error("Failed to submit lesson result, rolling back.", e);
            throw e; // Triggers rollback
        }
    }

    @Override
    @Transactional
    public LessonResult reviewLessonResult(Long resultId, LessonResult.Status status, String comment, Long teacherId) {
        LessonResult lr = lessonResultRepository.findById(resultId)
                .orElseThrow(() -> new ResourceNotFoundException("LessonResult not found: " + resultId));
        lr.setStatus(status);
        lr.setComment(comment);
        logger.info("Lesson result {} reviewed by teacher {} - status: {}", resultId, teacherId, status);
        return lessonResultRepository.save(lr);
    }

    public LessonResultDTO getLessonResultWithExamScore(Long lessonId, Long userId) {
        LessonResult result = lessonResultRepository.findByLessonIdAndUserId(lessonId, userId)
                .orElseThrow(() -> new ResourceNotFoundException("LessonResult not found"));
        LessonResultDTO dto = mapToDto(result);

        ExamDto exam = examClient.getExamByLessonId(lessonId);
        Integer examScore = null;
        if (exam != null && exam.getId() != null) {
            ExamResultDto examResult = examClient.getExamResult(exam.getId(), String.valueOf(userId));
            if (examResult != null) {
                examScore = examResult.getScore();
            }
        }
        dto.setExamScore(examScore);
        return dto;
    }

    private LessonResultDTO mapToDto(LessonResult result) {
        LessonResultDTO dto = new LessonResultDTO();
        dto.setId(result.getId());
        dto.setLessonId(result.getLesson().getId());
        dto.setUserId(result.getUser().getId());
        dto.setStatus(result.getStatus() != null ? result.getStatus().name() : null);
        dto.setUploadedFile(result.getUploadedFile());
        dto.setComment(result.getComment());
        dto.setUpdatedAt(result.getUpdatedAt());
        return dto;
    }
}