package com.example.demoregister.controller;



import com.example.demoregister.entity.*;
import com.example.demoregister.repository.QuestionRepository;
import com.example.demoregister.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class QuizController {
    Boolean submitted = false;
    static Long quizId;
    static Long idSubject;
    static Subject subject;
    static Integer correct;
    static Integer wrong;
    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserService userService;

    @Autowired
    private SubjectService subjectService;
    @Autowired
    private QuizListService quizListService;

    @Autowired
    QuizService qService;

    @RequestMapping("/Subject")
    public String subjectDetails(Model model) {
        model.addAttribute("subjects", subjectService.getAllSubject());
        return "Subject";
    }
    @RequestMapping("/scoreCard")
    public String score() {
        return "scoreCard";
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
        List question = questionService.listAllQuiz(quizId);
        System.out.println(question);
        model.addAttribute("qForm", questionService.listAllQuiz(quizId));

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
        subject = new Subject(subId,subName);
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
       subject =subjectService.getSubjectById(subjectId);
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
        String userans =req.getParameter("userans");
        String optionA=req.getParameter("optionA");
        String optionB=req.getParameter("optionB");
        String optionC=req.getParameter("optionC");
        String title=req.getParameter("title");

        Question question=new Question(quesId,quizId,subjectId,title,optionB,optionC,optionA,ans,userans);
        questionService.saveQuestion(question);

        model.addAttribute("qForm", questionService.listAllQuiz(quizId));
        return "Quiz";
    }

    @PostMapping("/submit")
    public String checkAns(HttpServletRequest request,Model model) {
          System.out.println(request.getParameter("ans"));
        correct=0;

        if (request.getParameter("userans").equals(request.getParameter("ans"))) {
            correct=correct+1;
        }

        if (request.getParameter("userans").equals(request.getParameter("ans"))) {
                correct=correct+1;

            }
        if (request.getParameter("userans").equals(request.getParameter("ans"))) {
            correct = correct + 1;
        }
        if (request.getParameter("userans").equals(request.getParameter("ans"))) {
                correct=correct+1;
//            System.out.println(request.getParameter("optionC"));
            }
        //System.out.println(correct);
        return "result";

    }
    //@RequestMapping(value = "/processForm", method = RequestMethod.POST)





}

