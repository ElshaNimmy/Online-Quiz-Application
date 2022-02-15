package com.example.demoregister.service;

import com.example.demoregister.entity.Subject;
import com.example.demoregister.entity.QuizList;

import com.example.demoregister.repository.QuizListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizListService {
    @Autowired
    public QuizListRepository repo;


    public List<QuizList> getQuizByQuizId(Subject subject) {
        return repo.findQuizBySubjectId(subject);

    }
    public QuizList saveQuizList(QuizList quizList){
        return repo.save(quizList);
    }


    public QuizList getQuizById(Long quizId) {
        return repo.findQuizById(quizId);
    }


}
