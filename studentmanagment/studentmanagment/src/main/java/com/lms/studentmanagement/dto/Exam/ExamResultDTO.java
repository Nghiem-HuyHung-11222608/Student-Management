package com.lms.studentmanagement.dto.Exam;

import lombok.Data;

@Data
public class ExamResultDTO {
    private int score;
    private int totalQuestions;
    private int attemptNumber;
    private boolean completed;
}
