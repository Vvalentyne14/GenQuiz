package com.example.BackendApplication2.Home;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String homePage() {
        return "home";
    }

    @GetMapping("/login")
    public String login() {
            return "login";
    }

    @GetMapping("/login-page")
    public String loginPage() {
        return "loginOption";
    }

    @GetMapping("/error")
    public String error() {
        return "error";
    }
    @GetMapping("/logout")
    public String logout() {
        return "logout";
    }
}