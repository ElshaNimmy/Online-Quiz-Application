package com.example.demoregister.repository;

import com.example.demoregister.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    @Query("SELECT question FROM Question question WHERE question.quizId = :id")
    List<Question> listAllQuiz(@Param("id") Long Id);
}
