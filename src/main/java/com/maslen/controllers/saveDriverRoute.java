package com.maslen.controllers;


import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@RestController
public class saveDriverRoute {
    @PostMapping("/saveDriverRoute")
    public ModelAndView indexPage(@Valid @RequestBody String str, Errors errors) {

        ModelAndView modelAndView = new ModelAndView();
        return modelAndView;
    }
}
