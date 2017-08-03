package com.maslen.controllers;

import com.maslen.beans.User;
import com.maslen.dao.Inf.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UsersController {

    private final UserDao userDao;

    @Autowired
    public UsersController(UserDao userDao) {
        this.userDao = userDao;
    }


    @GetMapping(value = "/")
    public ModelAndView indexPage() {

        return new ModelAndView("index", "user", new User());
    }

    @PostMapping(value = "/user")
    public ModelAndView addUser(@ModelAttribute("user") User user, BindingResult bindingResult, ModelAndView modelAndView) {
        if (!bindingResult.hasErrors()) {
            userDao.addUser(user);
        }

        return modelAndView;
    }
}
