package com.maslen.controllers;

import com.maslen.beans.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ModelAndView indexPage() {
        return new ModelAndView("index", "user", new User());
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ModelAndView addUser(@ModelAttribute("user") User user, BindingResult bindingResult, ModelAndView modelAndView) {



        return modelAndView;
    }
}
