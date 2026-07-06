package com.apps.quantitymeasurement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.apps.quantitymeasurement.security.OAuth2LoginSuccessHandler;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.apps.quantitymeasurement.security.CustomOAuth2UserService;
import com.apps.quantitymeasurement.security.JwtAuthenticationFilter;
import com.apps.quantitymeasurement.security.JwtAuthenticationEntryPoint;
@Configuration
public class SecurityConfig {
    @Autowired
    private OAuth2LoginSuccessHandler successHandler;
    @Autowired
    private JwtAuthenticationFilter jwtFilter;
    @Autowired
    private CustomOAuth2UserService customOAuth2UserService;
    @Autowired
    private JwtAuthenticationEntryPoint authenticationEntryPoint;
    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {

        http

                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth

                        .requestMatchers(
                                "/",
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/api-docs/**",
                                "/oauth2/**",
                                "/login/**",
                                "/api/auth/**",
                                "/h2-console/**")
                        .permitAll()

                        .anyRequest()
                        .authenticated())
                .exceptionHandling(

                        exception ->

                                exception.authenticationEntryPoint(

                                        authenticationEntryPoint))
                .oauth2Login(oauth -> oauth
                        .userInfoEndpoint(user ->
                                user.userService(customOAuth2UserService))
                        .successHandler((request, response, authentication) -> {

                            System.out.println("SUCCESS HANDLER FROM SECURITY CONFIG");

                            response.setContentType("text/plain");
                            response.getWriter().write("LOGIN SUCCESS");

                        })
                );

        http.headers(headers ->

                headers.frameOptions(
                        frame -> frame.disable()));
        http.addFilterBefore(

                jwtFilter,

                UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}