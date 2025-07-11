package com.lms.studentmanagement.client.exam.impl;


import com.lms.studentmanagement.client.exam.ExamClient;
import com.lms.studentmanagement.config.ServiceConfig;
import com.lms.studentmanagement.dto.exam.response.ExamDto;
import com.lms.studentmanagement.dto.exam.response.ExamResultDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExamClientImpl implements ExamClient {

    private final RestTemplate restTemplate;
    private final ServiceConfig serviceConfig;

    @Override
    public ExamDto getExamByLessonId(Long lessonId) {
        String url = serviceConfig.getExamBaseUrl() + "/exams/by-lesson/" + lessonId;
        log.info("Calling Exam Service to get Exam for lessonId={}", lessonId);
        ResponseEntity<ExamDto> response = restTemplate.getForEntity(url, ExamDto.class);
        return response.getBody();
    }

    @Override
    public ExamResultDto getExamResult(Long examId, String userId) {
        String url = serviceConfig.getExamBaseUrl() + "/exams/" + examId + "/result?userId=" + userId;
        log.info("Calling Exam Service to get ExamResult for examId={} userId={}", examId, userId);
        ResponseEntity<ExamResultDto> response = restTemplate.getForEntity(url, ExamResultDto.class);
        return response.getBody();
    }
}