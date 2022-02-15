package com.example.demoregister.controller;

import com.example.demoregister.entity.Admin;
import com.example.demoregister.entity.User;
import com.example.demoregister.service.AdminService;
import com.example.demoregister.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Objects;

@Controller
public class UserController {
    public static String username;
    public static Long userId;


    @Autowired
    private UserService service;
    @Autowired
    private AdminService adminService;

    @RequestMapping("/")
    public String index() {
        return "Login";
    }

    @RequestMapping("/Register")
    public String register() {
        return "Register";
    }

    @PostMapping("/Register")
    public String userRegistration(HttpServletRequest req, Model model) {
        if (!(req.getParameter("password").equals(req.getParameter("confirmPassword")))) {
            model.addAttribute("message", "password doesn't match");
            return "Register";
        }

        User user = new User(req.getParameter("username"), req.getParameter("email"), req.getParameter("password"));
        service.saveUser(user);
        return "Login";

    }

    @RequestMapping("/Home")
    public String home() {
        return "Home";
    }

    @PostMapping("/Home")
    public String validateUser(HttpServletRequest request, Model model) {
        User user = service.identifyUserByUsername(request.getParameter("username"));
        username = request.getParameter("username");
        if (!(Objects.isNull(user))) {

            if ((user.getPassword()).equals(request.getParameter("password"))) {
                HttpSession session = request.getSession();
                session.setAttribute("username",user.getUsername());
                session.setAttribute("userId",String.valueOf(user.getId()));
                return "Home";
            } else {
                model.addAttribute("message", "Invalid password!!");
                return "Login";
            }
        } else {
            Admin admin = adminService.identifyAdminByUsername(username);
            if (!(Objects.isNull(admin))) {
                if (admin.getPassword().equals(request.getParameter("password"))) {
                    return "HomeAdmin";
                } else {
                    model.addAttribute("message", "Invalid password!!");
                    return "Login";
                }
            } else {
                model.addAttribute("message", "Invalid credentials!!");
                return "Login";
            }
        }
    }
    @RequestMapping("/HomeAdmin")
    public String homeAdmin() {
        return "HomeAdmin";
    }

    @RequestMapping("/adminRegister")
    public String adminRegister() {
        return "adminRegister";
    }

    @PostMapping("/adminLogin")
    public String adminRegister(HttpServletRequest request, Model model) {
        if (!(request.getParameter("password").equals(request.getParameter("confirmPassword")))) {
            model.addAttribute("message", "password doesn't match");
            return "adminRegister";
        }
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Admin admin = new Admin(username, password);
        adminService.saveAdmin(admin);

        return "Login";

    }

    @GetMapping("/success")
    public String login(Principal principal){
        String username= principal.getName();
        if (username.equals("Admin")){
            return "redirect:/HomeAdmin";
        }
        User user =service.identifyUserByUsername(username);
        userId=user.getId();
        return "redirect:/Home";
    }

}





