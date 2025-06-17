package com.example.studentmanagment.mapper;

import com.example.studentmanagment.dto.CourseDTO;
import com.example.studentmanagment.dto.LessonDTO;
import com.example.studentmanagment.model.Course;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

public class CourseMapper {
    public static CourseDTO toCourseDTO(Course course) {
        if (Objects.isNull(course)) return null;

        CourseDTO dto = CourseDTO.builder()
                .id(course.getId())
                .title(course.getTitle())
                .description(course.getDescription())
                .publishedAt(course.getPublishedAt())
                .build();

        if (course.getLessons() != null) {
            dto.setLessons(course.getLessons().stream().map(lesson ->
                 LessonDTO.builder()
                        .id(lesson.getId())
                        .title(lesson.getTitle())
                        .content(lesson.getContent())
                        .videoLink(lesson.getVideoLink())
                        .courseId(course.getId())
                        .build()
            ).collect(Collectors.toList()));
        }
        
        return dto;
    }
}