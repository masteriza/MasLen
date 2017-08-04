package com.maslen.controllers;

import com.maslen.dao.Inf.UserDao;
import com.maslen.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UsersController {

    //    @Autowired
//    @Qualifier("passwordValidator")
    private Validator validator;

    private final UserDao userDao;


    @Autowired
    public UsersController(UserDao userDao, @Qualifier("passwordValidator") Validator validator) {
        this.userDao = userDao;
        this.validator = validator;

    }

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }


    @GetMapping(value = "/")
    public ModelAndView indexPage() {
        return new ModelAndView("index", "user", new User());
    }

    @PostMapping(value = "/user")
    public ModelAndView addUser(@ModelAttribute("user") User user, BindingResult bindingResult, ModelAndView modelAndView) {


        if (!bindingResult.hasErrors()) {


            userDao.isRegisteredEmail(user.getEmail());

            userDao.addUser(user);
        }

        return modelAndView;
    }
}
