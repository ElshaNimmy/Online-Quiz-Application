package com.example.demoregister.controller;

import com.example.demoregister.entity.Admin;
import com.example.demoregister.entity.User;
import com.example.demoregister.service.AdminService;
import com.example.demoregister.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
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

    @PostMapping("/Login")
    public String userRegistration(HttpServletRequest req, Model model) {
        if (!(req.getParameter("password").equals(req.getParameter("confirmPassword")))) {
            model.addAttribute("message", "password doesn't match");
            return "Register";
        }
        User user = new User(req.getParameter("username"), req.getParameter("email"), req.getParameter("password"));
        service.saveUser(user);
        return "Login";

    }

            @PostMapping("/Home")
            public String validateUser(HttpServletRequest request, Model model) {
                User user = service.identifyUserByUserName(request.getParameter("username"));
                username = request.getParameter("username");
                if (!(Objects.isNull(user))) {

                    if ((user.getPassword()).equals(request.getParameter("password"))) {
                        return "Home";
                    } else {
                        model.addAttribute("message", "Invalid password!!");
                        return "Login";
                    }
                }
                else {
                    Admin admin = adminService.identifyAdminByAdminName(username);
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

    @RequestMapping("/Home")
    public String home() {
        return "Home";
    }
    @RequestMapping("/HomeAdmin")
        public String homeAdmin() {
            return "HomeAdmin";
        }
    @PostMapping("/Login")
    public String userRegistration(HttpServletRequest req, Model model) {
        if (!(req.getParameter("password").equals(req.getParameter("confirmPassword")))) {
            model.addAttribute("message", "password doesn't match");
            return "Register";
        }
        User user = new User(req.getParameter("username"), req.getParameter("email"), req.getParameter("password"));
        service.saveUser(user);
        return "Login";

    }


}





