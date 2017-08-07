package com.maslen.controllers;

import com.maslen.dao.Inf.UserDao;
import com.maslen.models.User;
import com.maslen.utils.interfaces.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UsersController {
    private final UserDao userDao;

    private final RegistrationService registrationService;


    @Autowired
    public UsersController(UserDao userDao, RegistrationService registrationService) {
        this.userDao = userDao;
        this.registrationService = registrationService;
    }

    @RequestMapping(value = "/user")
    public User addUser(@Valid @RequestBody User user, BindingResult bindingResult) {

        if (registrationService.validateForm(user, bindingResult)) {
            user.setPassword(registrationService.encodePassword(user.getRawPassword()));
            userDao.addUser(user);
        } else {

        }


        return null;


    }
}
