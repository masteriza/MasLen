package com.maslen.controllers;

import com.maslen.dao.interfaces.UserDao;
import com.maslen.models.*;
import com.maslen.utils.interfaces.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@RestController
public class UserController {

    private final UserDao userDao;
    private final RegistrationService registrationService;

    @Autowired
    public UserController(UserDao userDao, RegistrationService registrationService) {
        this.userDao = userDao;
        this.registrationService = registrationService;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView registrationPage() {
        return new ModelAndView("registration");
    }

    @GetMapping(value = "/restorePassword")
    public ModelAndView restorePasswordPage() {
        return new ModelAndView("restorePassword");
    }

    @PostMapping(value = "/restorePassword")
    public EmailResponseBody restorePassword(@Valid @RequestBody EmailDto emailDto, BindingResult bindingResult) {
        EmailResponseBody emailResponseBody = new EmailResponseBody();
        ////---///
//        User user = userDao.searchUserByEmail(emailDto.getEmail()).orElseThrow();

        return new EmailResponseBody();
    }

    @PostMapping(value = "/user")
    public ValidationResponse addUser(@Valid @RequestBody RegistrationUserDto registrationUserDto, BindingResult bindingResult) {

        ValidationResponse response = new ValidationResponse();
//        if (!bindingResult.hasErrors()) {
        if (registrationService.validateForm(registrationUserDto, bindingResult)) {
            User user = registrationService.userDtoToUser(registrationUserDto);
            user.setPassword(registrationService.encodePassword(user.getPassword()));
            userDao.addUser(user);
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

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/userPanel", method = RequestMethod.GET)
    public ModelAndView userPanelPage() {
        return new ModelAndView("userPanel");
    }

}
