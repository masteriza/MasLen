package com.maslen.controllers;

import com.maslen.models.RegistrationUserDto;
import com.maslen.models.ValidationResponse;
import com.maslen.utils.interfaces.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UsersController {
//    private final UserDao userDao;

    private final RegistrationService registrationService;


    @Autowired
    public UsersController(/*UserDao userDao,*/ RegistrationService registrationService) {
//        this.userDao = userDao;
        this.registrationService = registrationService;
    }

    @RequestMapping(value = "/user")


    public ValidationResponse addUser(@Valid @RequestBody RegistrationUserDto registrationUserDto, BindingResult bindingResult) {

        ValidationResponse res = new ValidationResponse();
        if (!bindingResult.hasErrors()) {
//            userList.add(user);
            res.setStatus("SUCCESS");
//            res.setResult(userList);
        } else {
            res.setStatus("FAIL");
            res.setErrorList(bindingResult.getAllErrors());
        }

        return res;


//        if (registrationService.validateForm(user, bindingResult)) {
//            user.setPassword(registrationService.encodePassword(user.getRawPassword()));
//            userDao.addUser(user);
//        } else {
//
//        }
    }
}
