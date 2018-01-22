package com.maslen.controllers;

import com.maslen.dao.interfaces.UserDao;
import com.maslen.models.MessageResponseBody;
import com.maslen.models.RegistrationUserDto;
import com.maslen.models.User;
import com.maslen.services.interfaces.CommonService;
import com.maslen.services.interfaces.RegistrationService;
import com.maslen.utils.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@RestController
public class RegistrationController {

    private static final String EMAIL_NOT_REGISTERED = "Email is not registered";
    private static final String EMAIL_NOT_CONFIRMED = "Your Email has not been activated. Check your email and follow the instructions";

    private final UserDao userDao;
    private final RegistrationService registrationService;
    private final CommonService commonService;
    private final MailService mailService;

    @Autowired
    public RegistrationController(UserDao userDao,
                                  RegistrationService registrationService,
                                  CommonService commonService,
                                  MailService mailService) {
        this.userDao = userDao;
        this.registrationService = registrationService;
        this.commonService = commonService;
        this.mailService = mailService;
    }

    @GetMapping(value = "/registration")
    public ModelAndView registrationPage() {
        return new ModelAndView("registration");
    }

    @PostMapping(value = "/user")
    public MessageResponseBody addUser(@Valid @RequestBody RegistrationUserDto registrationUserDto, BindingResult bindingResult) {

        MessageResponseBody response = new MessageResponseBody();

        if (!registrationService.validateForm(registrationUserDto, bindingResult).hasErrors()) {
            User user = registrationService.userDtoToUser(registrationUserDto);
            user.setPassword(commonService.encryptPassword(user.getPassword()));
            userDao.addUser(user);
            mailService.sendRegistrationConfirmationEmail(user.getEmail());

            response.setStatus("OK");
        } else {
            response.setStatus("FAIL");
            response.setErrorList(bindingResult.getAllErrors());
        }

        return response;
    }

    @GetMapping(value = "/registrationSuccess")
    public ModelAndView registrationSuccessPage() {
        return new ModelAndView("registrationSuccess");
    }

    @GetMapping(value = "/confirmRegistration")
    public ModelAndView processConfirmationEmail(@RequestParam(value = "p", required = false) String decryptEmail) {
        ModelAndView modelAndView = new ModelAndView("activationFailed");
        try {
            String email = commonService.decrypt(decryptEmail);
            if (userDao.activateUser(email)) {
                modelAndView.setViewName("activationSuccessful");
            }
        } catch (RuntimeException e) {
            System.out.println("Мы так и не смогли расшифровать емайл или его не прислали");
        }
        return modelAndView;
    }

}
