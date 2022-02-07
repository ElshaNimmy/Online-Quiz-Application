package com.example.demoregister.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;


@Entity
public class QuizList {

    @Id
    private Long quizId;

    @Column(nullable = false)
    private String quizName;

    @Column(nullable = false)
    private String level;

    @Column(nullable = false )
    private Integer score;

    @JsonIgnore
    @ManyToOne
    private Subject subject;

    public QuizList(){

    }
    public QuizList(Long quizId, String quizName, String level,Integer score){
        this.quizId=quizId;
        this.quizName=quizName;
        this.level=level;
        this.score=score;
    }
    public Long getQuizId() {
        return quizId;
    }

    public void setQuizId(Long quizId) {
        this.quizId = quizId;
    }

    public String getQuizName() {
        return quizName;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
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



}
