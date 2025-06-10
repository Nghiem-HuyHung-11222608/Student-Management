package com.example.studentmanagment.dto;

import lombok.Data;

@Data
public class LessonDTO {
    private Long id;
    private String title;
    private String content;
    private String videoLink;
    private Long courseId;
}