package com.example.demoregister.entity;

import org.springframework.stereotype.Component;

import javax.persistence.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
    @Entity
    @Table(name = "questions")
    public class Question {
        @Id
        private Long quesId;
        private Long quizId;
        private Long subjectId;
        private String title;
        private String optionA;
        private String optionB;
        private String optionC;
        private String ans;
        private String userans;

        public Question() {

        }

        public Question(Long quesId,Long quizId, Long subjectId,String title, String optionA, String optionB, String optionC, String ans, String userans) {
            this.userans=userans;
            this.quizId=quizId;
            this.quesId = quesId;
            this.title = title;
            this.optionA = optionA;
            this.optionB = optionB;
            this.optionC = optionC;
            this.ans = ans;
            this.subjectId= subjectId;
        }
        public long getQuizId() {
            return quizId;
        }

        public void setQuizId(long quizId) {
            this.quizId = quizId;
        }


        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getOptionA() {
            return optionA;
        }

        public void setOptionA(String optionA) {
            this.optionA = optionA;
        }

        public String getOptionB() {
            return optionB;
        }

        public void setOptionB(String optionB) {
            this.optionB = optionB;
        }

        public String getOptionC() {
            return optionC;
        }

        public void setOptionC(String optionC) {
            this.optionC = optionC;
        }
        public Long getQuesId() {
            return quesId;
        }

        public void setQuesId(Long quesId) {
            this.quesId = quesId;
        }

        public void setQuizId(Long quizId) {
            this.quizId = quizId;
        }

        public Long getSubjectId() {
            return subjectId;
        }

        public void setSubjectId(Long subjectId) {
            this.subjectId = subjectId;
        }

        public String getAns() {
            return ans;
        }

        public void setAns(String ans) {
            this.ans = ans;
        }

        public String getUserans() {
            return userans;
        }

        public void setUserans(String userans) {
            this.userans = userans;
        }




//        @Override
//        public String toString() {
//            return "Question [quesId=" + quesId + ", title=" + title + ", optionA=" + optionA + ", optionB=" + optionB + ", optionC=" + optionC + ", ans=" + ans + ", userans=" + userans + "]";
//        }

    }

