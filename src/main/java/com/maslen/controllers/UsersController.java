package com.maslen.controllers;

import com.maslen.beans.User;
import com.maslen.dao.Inf.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UsersController {

    private final UserDao userDao;

    @Autowired
    public UsersController(UserDao userDao) {
        this.userDao = userDao;
    }


    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ModelAndView indexPage() {
        return new ModelAndView("index", "user", new User());
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ModelAndView addUser(@ModelAttribute("user") User user, BindingResult bindingResult, ModelAndView modelAndView) {

        userDao.findUserById(1);

        return modelAndView;
    }
}
