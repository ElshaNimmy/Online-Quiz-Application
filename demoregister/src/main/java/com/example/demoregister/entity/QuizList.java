package com.example.demoregister.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;


@Entity
public class QuizList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long quizId;

    @Column(nullable = false)
    private String level;

    @Column(nullable = false )
    private Integer score;

    @JsonIgnore
    @ManyToOne
    private Subject subject;

    public QuizList(){

    }

    public QuizList(Subject subject, String level,Long quizId,Integer score){
        this.subject=subject;
        this.quizId=quizId;
        this.level=level;
        this.score=score;

    }
    public QuizList(Long quizId, String level,Integer score){
        this.quizId=quizId;
        this.level=level;
        this.score=score;
    }

    public Long getQuizId() {
        return quizId;
    }

    public void setQuizId(Long quizId) {
        this.quizId = quizId;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }


}
