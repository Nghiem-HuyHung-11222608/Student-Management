package com.lms.studentmanagement.dto.exam.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamAttemptHistoryDto {
    private List<ExamAttemptResponseDto> attempts;
}