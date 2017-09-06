package com.maslen.controllers;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthenticationController {
    private static final String LOGIN = "logIn";
    private static final String LOGOUT = "logging/logOut";
    private static final String LOGIN_SUCCESS = "userPanel";
    private static final String LOGOUT_SUCCESS = "logging/logOutSuccessful";
    private static final String INDEX = "index";
    private static final String ERROR = "error";

    @GetMapping(value = "/logIn")
    public String showLogInForm() {
        String view = LOGIN;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            view = LOGIN_SUCCESS;
        }
        return view;
    }

    @PostMapping(value = "/loggedIn")
    public String processLogIn() {
        return LOGIN_SUCCESS;
    }

    @GetMapping(value = "/logOut")
    public String showLogOutPage() {
        return LOGOUT;
    }

    @GetMapping(value = "/loggedOut")
    public String processLogOut() {
        return LOGOUT_SUCCESS;
    }

    @GetMapping(value = "/logOutRefuse")
    public String processLogOutRefuse() {
        return INDEX;
    }

    @GetMapping(value = "/error")
    public String error(){
        return ERROR;
    }
}
