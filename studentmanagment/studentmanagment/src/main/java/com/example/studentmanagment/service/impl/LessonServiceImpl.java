package com.example.studentmanagment.service.impl;

import com.example.studentmanagment.model.Lesson;
import com.example.studentmanagment.exception.ResourceNotFoundException;
import com.example.studentmanagment.model.Course;
import com.example.studentmanagment.repository.CourseRepository;
import com.example.studentmanagment.repository.LessonRepository;
import com.example.studentmanagment.service.LessonService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class LessonServiceImpl implements LessonService {
    private final LessonRepository lessonRepository;
    private final CourseRepository courseRepository;

    public LessonServiceImpl(LessonRepository lessonRepository, CourseRepository courseRepository) {
        this.lessonRepository = lessonRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    @Transactional
    public Lesson createLesson(Long courseId, Lesson lesson, Long teacherId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found: " + courseId));
        lesson.setCourse(course);
        return lessonRepository.save(lesson);
    }

    @Override
    @Transactional
    public Lesson updateLesson(Long lessonId, Lesson lesson, Long teacherId) {
        Lesson existing = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new ResourceNotFoundException("Lesson not found: " + lessonId));
        existing.setTitle(lesson.getTitle());
        existing.setContent(lesson.getContent());
        existing.setVideoLink(lesson.getVideoLink());
        return lessonRepository.save(existing);
    }

    @Override
    @Transactional
    public void deleteLesson(Long lessonId, Long teacherId) {
        if (!lessonRepository.existsById(lessonId)) {
            throw new ResourceNotFoundException("Lesson not found: " + lessonId);
        }
        lessonRepository.deleteById(lessonId);
    }
}