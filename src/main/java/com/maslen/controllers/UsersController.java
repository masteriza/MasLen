package com.maslen.controllers;

import com.maslen.utils.Validators;
import com.maslen.dao.Inf.UserDao;
import com.maslen.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class UsersController {
    private final UserDao userDao;

    private final Validators validators;

    @Autowired
    public UsersController(UserDao userDao, Validators validators) {
        this.userDao = userDao;
        this.validators = validators;
    }

    @RequestMapping(value = "/user")
    public User addUser(@RequestBody User user) {


        return null;


    }
}
