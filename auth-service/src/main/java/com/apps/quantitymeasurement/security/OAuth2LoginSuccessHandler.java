package com.apps.quantitymeasurement.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Component
public class OAuth2LoginSuccessHandler
        implements AuthenticationSuccessHandler {

    @Autowired
    private JwtService jwtService;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication)
            throws IOException {

        OAuth2User user = (OAuth2User) authentication.getPrincipal();

        String email = user.getAttribute("email");

        String token = jwtService.generateToken(email);

        System.out.println("===== SUCCESS HANDLER EXECUTED =====");
        System.out.println("Generated JWT : " + token);

//        response.sendRedirect("/login-success.html?token=" + token);
        String name = user.getAttribute("name");
        String picture = user.getAttribute("picture");


        response.sendRedirect(
                "http://ec2-13-49-123-197.eu-north-1.compute.amazonaws.com/login?token=" + token +
                        "&name=" + URLEncoder.encode(name, StandardCharsets.UTF_8) +
                        "&email=" + URLEncoder.encode(email, StandardCharsets.UTF_8) +
                        "&picture=" + URLEncoder.encode(picture, StandardCharsets.UTF_8)
        );
    }
}