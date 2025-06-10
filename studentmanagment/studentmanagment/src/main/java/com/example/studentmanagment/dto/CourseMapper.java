package com.example.studentmanagment.dto;

import com.example.studentmanagment.model.Course;

import com.example.studentmanagment.model.Course;
import com.example.studentmanagment.model.Lesson;
import java.util.stream.Collectors;

public class CourseMapper {
    public static CourseDTO toDTO(Course course) {
        if (course == null) return null;
        CourseDTO dto = new CourseDTO();
        dto.setId(course.getId());
        dto.setTitle(course.getTitle());
        dto.setDescription(course.getDescription());
        dto.setPublishedAt(course.getPublishedAt());
        if (course.getLessons() != null) {
            dto.setLessons(course.getLessons().stream().map(lesson -> {
                LessonDTO ldto = new LessonDTO();
                ldto.setId(lesson.getId());
                ldto.setTitle(lesson.getTitle());
                ldto.setContent(lesson.getContent());
                ldto.setVideoLink(lesson.getVideoLink());
                ldto.setCourseId(course.getId());
                return ldto;
            }).collect(Collectors.toList()));
        }
        return dto;
    }
}