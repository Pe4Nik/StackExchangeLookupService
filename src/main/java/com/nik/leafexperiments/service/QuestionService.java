package com.nik.leafexperiments.service;

import com.nik.leafexperiments.entity.Question;

import java.util.List;

public interface QuestionService {
    Question getQuestionById(Integer id);
    void saveQuestion(Question question);
    void saveAllQuestions(List<Question> questions);
    void updateQuestion(Integer id, int answers, boolean isAnswered);
    void deleteQuestion(Integer id);
    List<Question> getAllQuestions();
}
