package com.example.demoregister.controller;



import com.example.demoregister.entity.*;
import com.example.demoregister.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String quizList(@PathVariable Long subjectId, Model model){
        Subject subject = subjectService.getSubjectById(subjectId);
        idSubject=subject.getSubjectId();

        model.addAttribute("quizList",quizListService.getQuizByQuizId(subject));

        return "QuizList";
    }
    @RequestMapping("/Quiz/{id}")
    public String quiz(@PathVariable Long id, Model model){
        quizId = id;
        model.addAttribute("qForm", qService.getQuestions());

        return "Quiz";
    }
//@RequestMapping("/Quiz")
//public String quizDetails(Model model) {
//    model.addAttribute("qForm", qService.getQuestions());
//
//    return "Quiz";
//}
//}
//    @PostMapping("/Quiz")
//    public String quiz( Model m){
//    submitted = false;
//
//        QuestionForm qForm = qService.getQuestions();
//        m.addAttribute("qForm", qForm);
//
//        return "Quiz";
//    }
//    @PostMapping("/submit")
//    public String submit(@ModelAttribute QuestionForm qForm, Model m) {
//        if(!submitted) {
//            result.setTotalCorrect(qService.getResult(qForm));
//            qService.saveScore(result);
//            submitted = true;
//        }
//
//        return "result";
//    }
//
//    @GetMapping("/score")
//    public String score(Model m) {
//        List<Result> sList = qService.getTopScore();
//        m.addAttribute("sList", sList);
//
//        return "scoreboard.html";
//    }


}

