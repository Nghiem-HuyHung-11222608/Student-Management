package com.lms.studentmanagement.client.exam;

import com.lms.studentmanagement.dto.exam.response.ExamDto;
import com.lms.studentmanagement.dto.exam.response.ExamResultDto;

public interface ExamClient {
    ExamDto getExamByLessonId(Long lessonId);
    ExamResultDto getExamResult(Long examId, String userId);
}