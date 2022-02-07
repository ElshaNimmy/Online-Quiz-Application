package com.example.demoregister.controller;

import com.example.demoregister.entity.User;
import com.example.demoregister.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {
    public static String email;
    public static Long userId;
    @Autowired
    private UserService service;

    @RequestMapping("/")
    public String index() {
        return "Login";
    }

    @RequestMapping("/Register")
    public String register() {
        return "Register";
    }

    @PostMapping("/Login")
    public String userRegistration(HttpServletRequest req, Model model) {
        if (!(req.getParameter("password").equals(req.getParameter("confirmPassword")))) {
            model.addAttribute("message", "password doesn't match");
            return "Register";
        }
        User user = new User(req.getParameter("name"), req.getParameter("email"), req.getParameter("password"));
        service.saveUser(user);
        return "Login";

    }

    @RequestMapping("/Home")
    public String home() {
        return "Home";
    }

    @PostMapping("/Home")
    public String userLogin(HttpServletRequest request, Model model) {
        User user = service.identifyUserByEmail(request.getParameter("email"));
        if (user.getPassword().equals(request.getParameter("password"))) {
            email = request.getParameter("email");
            userId =user.getId();
            System.out.println(email);
            return "Home";
        } else {
            model.addAttribute("message", "Invalid Credientials");
            return "Login";
        }
    }
//    @RequestMapping("/Movies")
//    public String movieDetails(Model model) {
//    model.addAttribute("movies", movieService.getAllMovies());
//
//        return "Movies";
//    }



//    @RequestMapping("/MovieSlot/{movieId}")
//    public String movieShow(@PathVariable Long movieId, Model model){
//        Movie movie = movieService.getMovieById(movieId);
//        model.addAttribute("movieSlot",showService.getShowByMovieId(movie));
////        model.addAttribute("movieName",movie.getMovieName());
//        return "MovieSlot";
//    }




}
