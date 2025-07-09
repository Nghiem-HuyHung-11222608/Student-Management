package com.lms.studentmanagement.dto.exam.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionFeedbackDto {
    private Long questionId;
    private String questionText;
    private Integer correctAnswerIndex;
    private String correctAnswerText;
    private Integer chosenAnswerIndex;
    private String chosenAnswerText;
    private Boolean correct;
}