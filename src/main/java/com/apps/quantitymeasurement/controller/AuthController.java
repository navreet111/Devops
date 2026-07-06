package com.apps.quantitymeasurement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AuthController {

    @ResponseBody
    @GetMapping("/")
    public String home() {
        return "Quantity Measurement Application";
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
}