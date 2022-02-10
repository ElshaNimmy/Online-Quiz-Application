package com.example.demoregister.repository;

import com.example.demoregister.entity.Subject;
import com.example.demoregister.entity.QuizList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuizListRepository extends JpaRepository<QuizList,Long> {

    @Query("Select quizList from QuizList quizList where quizList.subject=:subject")
    List<QuizList> findQuizBySubjectId(Subject subject);

    @Query("Select quizList from QuizList quizList where quizList.quizId=:quizId")
//    QuizList findShowById(Long quizId);
    QuizList findQuizById(Long quizId);
}

