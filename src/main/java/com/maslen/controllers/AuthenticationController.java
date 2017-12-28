package com.maslen.controllers;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AuthenticationController {
    private static final String LOGIN = "login";
    private static final String LOGOUT = "logout";
    private static final String LOGIN_SUCCESS = "userPanel";
    private static final String LOGOUT_SUCCESS = "logOutSuccessful";
    private static final String INDEX = "indexPoint";
    private static final String ERROR = "error";


    //    @GetMapping(value = "/login")
    @RequestMapping(value = "/login", method = {RequestMethod.GET})
    public String showLogInForm() {
        String view = LOGIN;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            view = LOGIN_SUCCESS;
        }
        return view;
    }

    @RequestMapping(value = "/auth", method = {RequestMethod.POST})
    public String auth() {
        String view = LOGIN;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            view = LOGIN_SUCCESS;
        }
        return view;
    }


    //    @PostMapping(value = "/loggedIn")
    @RequestMapping(value = "/loggedIn", method = {RequestMethod.GET, RequestMethod.POST})
    public String processLogIn() {
//        return LOGIN_SUCCESS;
        return "redirect:userPanel.html";

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
