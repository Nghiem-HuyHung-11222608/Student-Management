package com.example.studentmanagment.dto;

import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
public class CourseDTO {
    private Long id;
    private String title;
    private String description;
    private Long createdById;
    private Date publishedAt;
    private List<LessonDTO> lessons;
}
