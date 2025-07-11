package com.lms.studentmanagement.controller;

import com.lms.studentmanagement.dto.LessonResultDTO;
import com.lms.studentmanagement.model.LessonResult;
import com.lms.studentmanagement.service.impl.LessonResultServiceImpl;
import com.lms.studentmanagement.service.LessonResultService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lesson-results")
public class LessonResultController {
    private final LessonResultService lessonResultService;
    private final LessonResultServiceImpl lessonResultServiceImpl;

    public LessonResultController(LessonResultService lessonResultService, LessonResultServiceImpl lessonResultServiceImpl) {
        this.lessonResultService = lessonResultService;
        this.lessonResultServiceImpl = lessonResultServiceImpl;
    }

    @PreAuthorize("hasRole('STUDENT')")
    @PostMapping("/submit/{lessonId}")
    public LessonResultDTO submitLessonResult(@PathVariable Long lessonId, @RequestBody LessonResult result, @RequestParam Long studentId) {
        LessonResult entity = lessonResultService.submitLessonResult(lessonId, result, studentId);
        return mapToLessonResultDTO(entity);
    }

    @PreAuthorize("hasRole('TEACHER')")
    @PatchMapping("/review/{resultId}")
    public LessonResultDTO reviewLessonResult(@PathVariable Long resultId, @RequestParam("status") LessonResult.Status status,
                                              @RequestParam("comment") String comment, @RequestParam("teacherId") Long teacherId) {
        LessonResult entity = lessonResultService.reviewLessonResult(resultId, status, comment, teacherId);
        return mapToLessonResultDTO(entity);
    }

    @GetMapping("/with-exam-score")
    public LessonResultDTO getLessonResultWithExamScore(@RequestParam Long lessonId, @RequestParam Long userId) {
        return lessonResultServiceImpl.getLessonResultWithExamScore(lessonId, userId);
    }

    // Helper to map entity to DTO (or use your own mapper)
    private LessonResultDTO mapToLessonResultDTO(LessonResult result) {
        LessonResultDTO dto = new LessonResultDTO();
        dto.setId(result.getId());
        dto.setLessonId(result.getLesson() != null ? result.getLesson().getId() : null);
        dto.setUserId(result.getUser() != null ? result.getUser().getId() : null);
        dto.setStatus(result.getStatus() != null ? result.getStatus().name() : null);
        dto.setUploadedFile(result.getUploadedFile());
        dto.setComment(result.getComment());
        dto.setUpdatedAt(result.getUpdatedAt());
        // examScore: set if needed, or leave null for submit/review
        return dto;
    }
}