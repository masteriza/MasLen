package com.maslen.controllers;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthenticationController {
    private static final String LOGIN = "login";
    private static final String LOGOUT = "logout";
    private static final String LOGIN_SUCCESS = "userPanel";
    private static final String LOGOUT_SUCCESS = "logOutSuccessful";
    private static final String INDEX = "indexPoint";
    private static final String ERROR = "error";


    @GetMapping(value = "/login")
    public String showLogInForm() {
        String view = LOGIN;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            view = LOGIN_SUCCESS;
        }
        return view;
    }

    @GetMapping(value = "/loggedIn")
    public String processLogIn() {
        return LOGIN_SUCCESS;
    }

    @GetMapping(value = "/logout")
    public String showLogOutPage() {
        return LOGOUT;
    }

    @GetMapping(value = "/loggedout")
    public String processLogOut() {
        return LOGOUT_SUCCESS;
    }

    @GetMapping(value = "/logOutRefuse")
    public String processLogOutRefuse() {
        return INDEX;
    }

    @GetMapping(value = "/error")
    public String error() {
        return ERROR;
    }
}
