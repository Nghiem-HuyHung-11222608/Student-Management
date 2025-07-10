package com.lms.studentmanagement.dto.exam.response;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamDto {
    private Long id;
    private String title;
    private Integer durationMinutes;
    private String location;
    private String timeslot;
    private List<QuestionDto> questions;
}