package com.maslen.controllers;

import com.maslen.dao.Inf.UserDao;
import com.maslen.models.User;
import com.maslen.utils.interfaces.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UsersController {
    private final UserDao userDao;

    private final UserValidator userValidator;

    @Autowired
    public UsersController(UserDao userDao, UserValidator userValidator) {
        this.userDao = userDao;
        this.userValidator = userValidator;
    }

    @RequestMapping(value = "/user")
    public User addUser(@Valid @RequestBody User user, BindingResult bindingResult) {


        return null;


    }
}
