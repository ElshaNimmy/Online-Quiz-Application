package com.example.demoregister.entity;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Result {

    @Id
    private Long id;
    private String username;
    private String quizLevel;
    private String subjectName;
    private Integer totalCorrect;
    private Integer totalWrong;


    public Result(){

    }
//    public Result(String username,String quizLevel,String  subjectName){
//        this.username=username;
//        this.quizLevel=quizLevel;
//        this.subjectName=subjectName;
//
//    }
public Long getId() {
    return id;
}

    public void setId(Long id) {
        this.id = id;
    }

    public void setTotalCorrect(Integer totalCorrect) {
        this.totalCorrect = totalCorrect;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getQuizLevel() {
        return quizLevel;
    }

    public void setQuizLevel(String quizLevel) {
        this.quizLevel = quizLevel;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getTotalCorrect() {
        return totalCorrect;
    }

    public void setTotalCorrect(int totalCorrect) {
        this.totalCorrect = totalCorrect;
    }
    public Integer getTotalWrong() {
        return totalWrong;
    }

    public void setTotalWrong(Integer totalWrong) {
        this.totalWrong = totalWrong;
    }


}
