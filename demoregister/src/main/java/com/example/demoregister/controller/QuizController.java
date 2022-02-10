package com.example.demoregister.controller;



import com.example.demoregister.entity.*;
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

    @RequestMapping("/QuizList/{subjectId}")
    public String quizList(@PathVariable Long subjectId, Model model) {
        Subject subject = subjectService.getSubjectById(subjectId);
        idSubject = subject.getSubjectId();

        model.addAttribute("quizList", quizListService.getQuizByQuizId(subject));

        return "QuizList";
    }

    @RequestMapping("/Quiz/{id}")
    public String quiz(@PathVariable Long id, Model model) {
        quizId = id;
        System.out.println(qService.getQuestions());
        model.addAttribute("qForm", qService.getQuestions());

        return "Quiz";
    }

    @RequestMapping("/UpdateSubject")
    public String sub() {
        return "UpdateSubject";
    }

    @PostMapping("/Subject")
    public String subjectUpdation(HttpServletRequest req, Model model) {
        String subName = req.getParameter("subjectName");
        Long subId = (Long.parseLong(req.getParameter("subjectId")));
        Subject subject = new Subject(subId, subName);
        subjectService.saveSubject(subject);
        model.addAttribute("subjects", subjectService.getAllSubject());
        return "Subject";

    }


//    @PostMapping("/QuizList/{subjectId}")
//    public String quizUpdation(HttpServletRequest req, Model model) {
//        Long subId = (Long.parseLong(req.getParameter("subjectId")));
//        Long quizId = (Long.parseLong(req.getParameter("quizId")));
//        String level = req.getParameter("level");
//        Integer score = Integer.parseInt(req.getParameter("score"));
//        QuizList quizList = new QuizList(subId, level, quizId, score);
//        quizListService.saveQuizList(quizList);
//        model.addAttribute("quizList", quizListService.getQuizByQuizId(subject));
//        return "QuizList";
//
//    }
}

