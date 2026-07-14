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
        System.out.println("=========== SECURITY CONFIG LOADED ===========");
        http

                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth

                        .requestMatchers(
                                "/",
                                "/login",
                                "/login.html",
                                "/login-success.html",
                                "/api/auth/**",
                                "/oauth2/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/api-docs/**",
                                "/h2-console/**",
                                "/css/**",
                                "/js/**"
                        )
                        .permitAll()

                        .anyRequest()
                        .authenticated())
                .exceptionHandling(

                        exception ->

                                exception.authenticationEntryPoint(

                                        authenticationEntryPoint))
                .oauth2Login(oauth ->

                        oauth

                                .userInfoEndpoint(user ->

                                        user.userService(customOAuth2UserService))

                                .successHandler(successHandler)

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