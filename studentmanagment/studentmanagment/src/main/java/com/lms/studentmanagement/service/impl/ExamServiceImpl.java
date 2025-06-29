package com.lms.studentmanagement.service.impl;

import com.lms.studentmanagement.dto.*;
import com.lms.studentmanagement.dto.Exam.*;
import com.lms.studentmanagement.model.Exam.*;
import com.lms.studentmanagement.model.*;
import com.lms.studentmanagement.repository.Exam.*;
import com.lms.studentmanagement.repository.UserRepository;
import com.lms.studentmanagement.service.ExamService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ExamServiceImpl implements ExamService {

    private final ExamRepository examRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final ExamAttemptRepository examAttemptRepository;
    private final UserRepository userRepository;

    public ExamServiceImpl(
            ExamRepository examRepository,
            QuestionRepository questionRepository,
            AnswerRepository answerRepository,
            ExamAttemptRepository examAttemptRepository,
            UserRepository userRepository
    ) {
        this.examRepository = examRepository;
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.examAttemptRepository = examAttemptRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ExamDTO getExamForLesson(Long lessonId) {
        Exam exam = examRepository.findByLessonId(lessonId);
        if (exam == null) throw new RuntimeException("Exam not found for lesson: " + lessonId);

        ExamDTO dto = new ExamDTO();
        dto.setId(exam.getId());
        dto.setLessonId(lessonId);
        dto.setQuestions(exam.getQuestions().stream().map(this::toQuestionDTO).collect(Collectors.toList()));
        return dto;
    }

    @Override
    @Transactional
    public ExamResultDTO submitExamAttempt(ExamAttemptDTO attemptDTO) {
        Exam exam = examRepository.findById(attemptDTO.getExamId())
                .orElseThrow(() -> new RuntimeException("Exam not found"));
        User user = userRepository.findById(attemptDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        int tries = examAttemptRepository.countByExamIdAndUserId(exam.getId(), user.getId());
        if (tries >= 3) throw new RuntimeException("No tries left for this exam");

        ExamAttempt attempt = new ExamAttempt();
        attempt.setExam(exam);
        attempt.setUser(user);
        attempt.setAttemptNumber(tries + 1);

        // Calculate score
        int score = 0;
        int total = exam.getQuestions().size();
        for (Question q : exam.getQuestions()) {
            Long userAnswerId = attemptDTO.getQuestionAnswers().get(q.getId());
            if (userAnswerId == null) continue;
            Answer answer = answerRepository.findById(userAnswerId)
                    .orElseThrow(() -> new RuntimeException("Answer not found"));
            if (answer.isCorrect()) {
                score++;
            }
        }
        attempt.setScore(score);
        attempt.setCompleted(true);
        examAttemptRepository.save(attempt);

        ExamResultDTO result = new ExamResultDTO();
        result.setScore(score);
        result.setTotalQuestions(total);
        result.setAttemptNumber(attempt.getAttemptNumber());
        result.setCompleted(true);
        return result;
    }

    @Override
    public int getTriesLeft(Long lessonId, Long userId) {
        Exam exam = examRepository.findByLessonId(lessonId);
        if (exam == null) throw new RuntimeException("Exam not found for lesson: " + lessonId);
        int tries = examAttemptRepository.countByExamIdAndUserId(exam.getId(), userId);
        return Math.max(0, 3 - tries);
    }

    private QuestionDTO toQuestionDTO(Question question) {
        QuestionDTO dto = new QuestionDTO();
        dto.setId(question.getId());
        dto.setContent(question.getContent());
        dto.setAnswers(question.getAnswers().stream().map(this::toAnswerDTO).collect(Collectors.toList()));
        return dto;
    }

    private AnswerDTO toAnswerDTO(Answer answer) {
        AnswerDTO dto = new AnswerDTO();
        dto.setId(answer.getId());
        dto.setContent(answer.getContent());
        return dto;
    }
}
