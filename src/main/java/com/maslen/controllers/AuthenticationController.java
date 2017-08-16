package com.maslen.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthenticationController {
    private static final String LOGOUT = "logOut";

    @GetMapping(value = "/logOut")
    public String showLogOutPage() {
        return LOGOUT;
    }
}
