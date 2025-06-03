package com.example.studentmanagment.service;

import com.example.studentmanagment.model.Lesson;

public interface LessonService {
    Lesson createLesson(Long courseId, Lesson lesson, Long teacherId);
    Lesson updateLesson(Long lessonId, Lesson lesson, Long teacherId);
    void deleteLesson(Long lessonId, Long teacherId);
}
