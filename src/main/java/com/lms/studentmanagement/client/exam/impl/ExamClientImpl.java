package com.lms.studentmanagement.client.exam.impl;

import com.lms.studentmanagement.client.exam.ExamClient;
import com.lms.studentmanagement.config.ServiceConfig;
import com.lms.studentmanagement.dto.exam.response.ExamDto;
import com.lms.studentmanagement.dto.exam.response.ExamAttemptRequestDto;
import com.lms.studentmanagement.dto.exam.response.ExamAttemptHistoryDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
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
        String baseUrl = serviceConfig.getExamBaseUrl();
        String url = baseUrl + "/api/exams/by-lesson/" + lessonId;

        log.info("Calling Exam Service to get Exam for lessonId={}", lessonId);
        ResponseEntity<ExamDto> response = restTemplate.getForEntity(url, ExamDto.class);

        return response.getBody();
    }

    @Override
    public ExamAttemptHistoryDto submitExamAttempt(Long examId, ExamAttemptRequestDto attemptRequestDto, String userId) {
        String baseUrl = serviceConfig.getExamBaseUrl();
        String url = baseUrl + "/api/exams/" + examId + "/attempts";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-User-Id", userId);

        HttpEntity<ExamAttemptRequestDto> request = new HttpEntity<>(attemptRequestDto, headers);

        log.info("Submitting exam attempt to Exam Service for examId={} and userId={}", examId, userId);
        ResponseEntity<ExamAttemptHistoryDto> response = restTemplate.postForEntity(url, request, ExamAttemptHistoryDto.class);

        return response.getBody();
    }
}