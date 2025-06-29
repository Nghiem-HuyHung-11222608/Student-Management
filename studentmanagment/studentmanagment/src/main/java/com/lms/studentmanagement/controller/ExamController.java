package com.lms.studentmanagement.controller;


import com.lms.studentmanagement.dto.*;
import com.lms.studentmanagement.dto.Exam.*;
import com.lms.studentmanagement.service.ExamService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/exams")
public class ExamController {

    private final ExamService examService;

    public ExamController(ExamService examService) {
        this.examService = examService;
    }

    @GetMapping("/lesson/{lessonId}")
    public ExamDTO getExamForLesson(@PathVariable Long lessonId) {
        return examService.getExamForLesson(lessonId);
    }

    @PostMapping("/submit")
    public ExamResultDTO submitExamAttempt(@RequestBody ExamAttemptDTO attemptDTO) {
        return examService.submitExamAttempt(attemptDTO);
    }

    @GetMapping("/lesson/{lessonId}/tries/{userId}")
    public int getTriesLeft(@PathVariable Long lessonId, @PathVariable Long userId) {
        return examService.getTriesLeft(lessonId, userId);
    }
}
