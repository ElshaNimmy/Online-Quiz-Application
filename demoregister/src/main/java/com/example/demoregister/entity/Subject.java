package com.example.demoregister.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

    @Entity
    public class Subject {

        @Id
        private Long subjectId;

        @Column(nullable = false)
        private String subjectName;


        @OneToMany
        private List<QuizList> quizList;

        public Subject(){

        }
        public Subject(Long subjectId, String subjectName) {

            this.subjectName = subjectName;

            this.subjectId=subjectId;

        }
        public Long getSubjectId() {
            return subjectId;
        }

        public void setSubjectId(Long subjectId) {
            this.subjectId = subjectId;
        }

        public String getSubjectName() {
            return subjectName;
        }

        public void setSubjectName(String subjectName) {
            this.subjectName = subjectName;
        }


    }


