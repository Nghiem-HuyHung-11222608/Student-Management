package com.lms.studentmanagement.dto.Exam;

import lombok.Data;
import java.util.List;

@Data
public class ExamDTO {
    private Long id;
    private Long lessonId;
    private List<QuestionDTO> questions;
}
