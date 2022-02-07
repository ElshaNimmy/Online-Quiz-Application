package com.example.demoregister.controller;



import com.example.demoregister.entity.*;
import com.example.demoregister.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class QuizController {
    Boolean submitted = false;
    static Long quizId;
    static Long idSubject;
    //  static Long idSeat;
    @Autowired
    private UserService userService;

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
        Subject subject = subjectService.getSubjectById(subjectId);
        idSubject=subject.getSubjectId();

        model.addAttribute("quizList",quizListService.getQuizByQuizId(subject));

        return "QuizList";
    }
    @RequestMapping("/Quiz")
    public String quiz(Model m){
        submitted = false;

//        QuestionForm qForm = qService.getQuestions();
//        m.addAttribute("qForm", qForm);

        return "Quiz";
    }

}

