package com.maslen.controllers;

import com.maslen.models.RegistrationUserDto;
import com.maslen.models.ValidationResponse;
import com.maslen.utils.interfaces.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {
//    private final UserDao userDao;

    private final RegistrationService registrationService;


    @Autowired
    public UserController(/*UserDao userDao,*/ RegistrationService registrationService) {
//        this.userDao = userDao;
        this.registrationService = registrationService;
    }

    @PostMapping(value = "/user")


    public ValidationResponse addUser(@Valid @RequestBody RegistrationUserDto registrationUserDto, BindingResult bindingResult) {

        ValidationResponse response = new ValidationResponse();
//        if (!bindingResult.hasErrors()) {
        if (registrationService.validateForm(registrationUserDto, bindingResult)) {
            response.setStatus("SUCCESS");
        } else {
            response.setStatus("FAIL");
            response.setErrorList(bindingResult.getAllErrors());
        }
        return response;


//        if (registrationService.validateForm(user, bindingResult)) {
//            user.setPassword(registrationService.encodePassword(user.getRawPassword()));
//            userDao.addUser(user);
//        } else {
//
//        }
    }
}
