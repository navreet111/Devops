package com.apps.quantitymeasurement.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AuthController {

    @GetMapping("/")
    public String home() {

        return "redirect:/oauth2/authorization/google";

    }

    @GetMapping("/api/auth/login")
    public String login() {
        return "redirect:/oauth2/authorization/google";
    }

    @ResponseBody
    @GetMapping("/login-success")
    public String success() {
        return "Login Successful";
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response,
                         Authentication authentication)
            throws ServletException {

        new SecurityContextLogoutHandler()
                .logout(request, response, authentication);

        return "redirect:/";
    }
}