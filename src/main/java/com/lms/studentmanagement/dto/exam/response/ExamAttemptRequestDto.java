package com.lms.studentmanagement.dto.exam.response;

import java.util.List;

public class ExamAttemptRequestDto {
    public String userId;
    public List<Integer> answers; // index per question
}