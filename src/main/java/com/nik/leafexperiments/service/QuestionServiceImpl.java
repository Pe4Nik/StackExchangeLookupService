package com.nik.leafexperiments.service;

import com.nik.leafexperiments.entity.Question;
import com.nik.leafexperiments.dao.QuestionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    public QuestionDAO questionDAO;

    @Override
    public Question getQuestionById(Integer id) {
        return questionDAO.getOne(id);
    }

    @Override
    public void saveQuestion(Question question) {
        questionDAO.save(question);
    }

    @Override
    public void updateQuestion(Integer id, int answers, boolean isAnswered) {
        Question question = questionDAO.getOne(id);
        question.setAnswersCount(answers);
        question.setAnswered(isAnswered);
        questionDAO.save(question);
    }

    @Override
    public void deleteQuestion(Integer id) {
        questionDAO.deleteById(id);
    }

    @Override
    public List<Question> getAllQuestions() {
        return questionDAO.findAll();
    }

    @Override
    public void saveAllQuestions(List<Question> questions) {
        questionDAO.saveAll(questions);
    }
}
