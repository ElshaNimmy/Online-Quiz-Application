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


    @RequestMapping("/Subject")
    public String subjectDetails(Model model) {
        model.addAttribute("subjects", subjectService.getAllSubject());
        return "Subject";
    }
    @RequestMapping("/QuizList/{subjectId}")
    public String quizList(@PathVariable Long subjectId, Model model){
     subject = subjectService.getSubjectById(subjectId);
        idSubject=subject.getSubjectId();
        model.addAttribute("quizList",quizListService.getQuizByQuizId(subject));
        return "QuizList";
    }
    @RequestMapping("/Quiz/{id}")
    public String quiz(@PathVariable Long id, Model model){
        quizId = id;
        List <Question> question = questionService.listAllQuizBySubject(quizId,subject.getSubjectId());
        model.addAttribute("qForm", question);
        return "Quiz";
    }
    @RequestMapping("/UpdateSubject")
    public String sub() {
        return "UpdateSubject";
    }

    @PostMapping("/Subject")
    public String subjectUpdation(HttpServletRequest req, Model model) {
        String subName=req.getParameter("subjectName");
        Long subId=(Long.parseLong(req.getParameter("subjectId")));
        Subject subject = new Subject(subId,subName);
        subjectService.saveSubject(subject);
        model.addAttribute("subjects", subjectService.getAllSubject());
        return "subjectView";
    }
    @RequestMapping("/UpdateQuiz")
    public String updateQuiz() {
        return "UpdateQuiz";
    }

    @PostMapping("/QuizList")
    public String quizUpdate(HttpServletRequest req ,Model model) {
       Long subjectId= Long.valueOf((req.getParameter("subjectId")));
       Subject subject =subjectService.getSubjectById(subjectId);
       Long quizId=(Long.parseLong(req.getParameter("quizId")));
       String level=req.getParameter("level");
       Integer score=Integer.parseInt(req.getParameter("score"));
       QuizList quizList=new QuizList(subject,level,quizId,score);
       quizListService.saveQuizList(quizList);
       model.addAttribute("quizList",quizListService.getQuizByQuizId(subject));
        return "quizListView";

    }
    @RequestMapping("/updateQuestions")
    public String updateQuestions() {
        return "updateQuestions";
    }

    @PostMapping("/Quiz")
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
        return "questionsView";
    }

    @PostMapping("/submit")
    public String checkAns(HttpServletRequest request, Model model, Principal principal) {
//        HttpSession session = request.getSession();
//        String username = (String) session.getAttribute("username");
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
//        score=correct*2;
//        model.addAttribute("score",score);
        return "result";

    }

    @PostMapping("/scoreCard")
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

        //listScoreByUsername(request.getParameter("username"));
        return "scoreCard";
    }
    private List listScoreByUsername(String username){
        List scoreCard1=scoreService.listScoreByUsername(username);
        //System.out.println(scoreCard1);
        return null;
    }






}

