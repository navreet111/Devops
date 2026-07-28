package com.apps.quantitymeasurement.security;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;

import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;

import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserService

        implements OAuth2UserService
        <OAuth2UserRequest, OAuth2User> {

    @Override
    public OAuth2User loadUser(

            OAuth2UserRequest userRequest)

            throws OAuth2AuthenticationException {

        DefaultOAuth2UserService service =

                new DefaultOAuth2UserService();

        OAuth2User user =

                service.loadUser(userRequest);

        System.out.println(

                "Google User Email : "

                        + user.getAttribute("email"));

        System.out.println(

                "Google User Name : "

                        + user.getAttribute("name"));

        System.out.println(

                "Google Picture : "

                        + user.getAttribute("picture"));

        return user;
    }
}