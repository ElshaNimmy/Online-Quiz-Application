package com.example.demoregister.service;

import com.example.demoregister.entity.Question;
import com.example.demoregister.entity.Subject;
import com.example.demoregister.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository repository;
   public Question saveQuestion(Question question){
       return repository.save(question);
   }
  public List<Question> listAllQuiz(Long id){
    return repository.listAllQuiz(id);
}
    public List<Question> listAllQuizBySubject(Long id,Long subjectId){
        return repository.listAllQuizBySubject(id,subjectId);
    }
}
