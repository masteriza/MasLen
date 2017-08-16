package com.maslen.controllers;

import com.maslen.models.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class IndexPageController {
    private static final String NAME_MODEL = "user";

    @RequestMapping("/")
    @PreAuthorize("hasRole('USER')")
    //@PreAuthorize("hasAnyRole('OWNER', 'ADMIN')")
    public ModelAndView indexPage() {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject(user);
        modelAndView.setViewName("index");
        //return "index";
        return modelAndView;
    }

    //    @RequestMapping("/registrationUser")
    @PostMapping("/registrationUser")
    public ModelAndView registrationUser(@Valid @ModelAttribute(NAME_MODEL)
                                                 User user, BindingResult bindingResult, ModelAndView modelAndView) {

        modelAndView.setViewName("index");
        return modelAndView;
    }

    @PostMapping("/loginUser")
    public ModelAndView loginUser(@Valid @ModelAttribute(NAME_MODEL)
                                          User user, BindingResult bindingResult, ModelAndView modelAndView) {

        modelAndView.setViewName("index");
        return modelAndView;
    }


}
