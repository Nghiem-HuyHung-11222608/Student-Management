package com.example.studentmanagment.controller;

import com.example.studentmanagment.model.Lesson;
import com.example.studentmanagment.service.LessonService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/lessons")
public class LessonController {
    private final LessonService lessonService;
    public LessonController(LessonService lessonService) { this.lessonService = lessonService; }

    @PreAuthorize("hasRole('TEACHER')")
    @PostMapping("/{courseId}")
    public Lesson createLesson(@PathVariable Long courseId, @RequestBody Lesson lesson, @RequestParam Long teacherId) {
        return lessonService.createLesson(courseId, lesson, teacherId);
    }

    @PreAuthorize("hasRole('TEACHER')")
    @PutMapping("/{lessonId}")
    public Lesson updateLesson(@PathVariable Long lessonId, @RequestBody Lesson lesson, @RequestParam Long teacherId) {
        return lessonService.updateLesson(lessonId, lesson, teacherId);
    }

    @PreAuthorize("hasRole('TEACHER')")
    @DeleteMapping("/{lessonId}")
    public void deleteLesson(@PathVariable Long lessonId, @RequestParam Long teacherId) {
        lessonService.deleteLesson(lessonId, teacherId);
    }
}

