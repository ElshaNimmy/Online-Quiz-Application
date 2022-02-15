package com.example.demoregister.controller;



import com.example.demoregister.entity.*;
import com.example.demoregister.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;

import java.security.Principal;

import java.util.List;

@Controller
public class QuizController {
    Boolean submitted = false;
    static Long id;
    static Long quizId;
    static Long idSubject;
    static Subject subject;
    static int score;
    static int correct=0;
    static int wrong=0;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private UserService userService;
    @Autowired
    private ScoreService scoreService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private QuizListService quizListService;

    @RequestMapping("/subject")//SubjectList of quiz
    public String subjectDetails(Model model) {
        model.addAttribute("subjects", subjectService.getAllSubject());
        return "subject";
    }

    @RequestMapping("/quiz-list/{subjectId}")//Quiz Level
    public String quizList(@PathVariable Long subjectId, Model model){
     subject = subjectService.getSubjectById(subjectId);
        idSubject=subject.getSubjectId();
        model.addAttribute("quizList",quizListService.getQuizByQuizId(subject));
        return "quiz-list";
    }

    @RequestMapping("/quiz/{id}")//Quiz questions n answers
    public String quiz(@PathVariable Long id, Model model){
        quizId = id;
        List <Question> question = questionService.listAllQuizBySubject(quizId,subject.getSubjectId());
        model.addAttribute("qForm", question);
        return "quiz";
    }

    @RequestMapping("/update-subject")
    public String sub() {
        return "update-subject";
    }

    @PostMapping("/subjects")//Adding quiz subject
    public String subjectUpdate(HttpServletRequest req, Model model) {
        String subName=req.getParameter("subjectName");
        Long subId=(Long.parseLong(req.getParameter("subjectId")));
        Subject subject = new Subject(subId,subName);
        subjectService.saveSubject(subject);
        model.addAttribute("subjects", subjectService.getAllSubject());
        return "subject-view";
    }

    @RequestMapping("/update-quiz")
    public String updateQuiz() {
        return "update-quiz";
    }

    @PostMapping("/quiz-list")//Adding quiz level
    public String quizUpdate(HttpServletRequest req ,Model model) {
       Long subjectId= Long.valueOf((req.getParameter("subjectId")));
       Subject subject =subjectService.getSubjectById(subjectId);
       Long quizId=(Long.parseLong(req.getParameter("quizId")));
       String level=req.getParameter("level");
       Integer score=Integer.parseInt(req.getParameter("score"));
       QuizList quizList=new QuizList(subject,level,quizId,score);
       quizListService.saveQuizList(quizList);
       model.addAttribute("quizList",quizListService.getQuizByQuizId(subject));
        return "quiz-list-view";
    }

    @RequestMapping("/update-questions")
    public String updateQuestions() {
        return "update-questions";
    }

    @PostMapping("/quiz")//Adding quiz questions part
    public String questionUpdate(HttpServletRequest req, Model model) {
        Long quizId=(Long.parseLong(req.getParameter("quizId")));
        Long quesId=(Long.parseLong(req.getParameter("quesId")));
        Long subjectId=(Long.parseLong(req.getParameter("subjectId")));
        String ans=req.getParameter("ans");
        String optionA=req.getParameter("optionA");
        String optionB=req.getParameter("optionB");
        String optionC=req.getParameter("optionC");
        String title=req.getParameter("title");
        Question question=new Question(quesId,quizId,subjectId,title,optionB,optionC,optionA,ans);
        questionService.saveQuestion(question);
        model.addAttribute("qForm", questionService.listAllQuiz(quizId));
        return "questions-view";
    }

    @PostMapping("/submit")//Calculating result of quiz
    public String checkAns(HttpServletRequest request, Model model, Principal principal) {
        correct=0;
        wrong=0;
        String username=principal.getName();
        for(int i=0;i< Integer.parseInt(request.getParameter("arrayLength"));i++) {
            if (request.getParameter("qForm[" + i + "]").equals(request.getParameter("qForm[" + i + "].ans"))) {
                correct++;

            }
            else{
                wrong++;
            }
        }
        QuizList quizList = quizListService.getQuizById(Long.parseLong(request.getParameter("quizId")));
        Subject subject = subjectService.getSubjectById(Long.parseLong(request.getParameter("subId")));
        model.addAttribute("correctAns",correct);
        model.addAttribute("wrongAns",wrong);
        model.addAttribute("quizDetails",quizList);
        model.addAttribute("subjectDetails",subject);
        model.addAttribute("username",username);
        return "result";

    }

    @PostMapping("/score")//displaying score history
    public String scoreCard(HttpServletRequest request,Model model,Principal principal){
        String username=principal.getName();
        Score score=new Score();
        score.setScore(Integer.parseInt(request.getParameter("correctans"))*2);
        score.setUsername(request.getParameter("username"));
        score.setSubjectName(request.getParameter("subjectname"));
        score.setLevel(request.getParameter("level"));
        scoreService.saveScore(score);
        List<Score> scores = scoreService.listScoreByUsername(username);
        model.addAttribute("scores",scores);

        return "score";
      }
    private List listScoreByUsername(String username){
        List scoreCard1=scoreService.listScoreByUsername(username);

        return null;
    }






}

