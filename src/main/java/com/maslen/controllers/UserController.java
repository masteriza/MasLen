package com.maslen.controllers;

//import com.maslen.dao.interfaces.UserDao;

import com.maslen.dao.interfaces.UserDao;
import com.maslen.models.*;
import com.maslen.utils.MailService;
import com.maslen.utils.interfaces.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@RestController
public class UserController {

    private final UserDao userDao;
    private final RegistrationService registrationService;
    private final MailService mailService;

    @Autowired
    public UserController(UserDao userDao, RegistrationService registrationService, MailService mailService) {
        this.userDao = userDao;
        this.registrationService = registrationService;
        this.mailService = mailService;
    }

    @GetMapping(value = "/registration")
    public ModelAndView registrationPage() {
        return new ModelAndView("registration");
    }

    @GetMapping(value = "/registrationSuccess")
    public ModelAndView registrationSuccessPage() {
        return new ModelAndView("registrationSuccess");
    }


    @GetMapping(value = "/restorePassword")
    public ModelAndView restorePasswordPage() {
        return new ModelAndView("restorePassword");
    }

    @PostMapping(value = "/restorePassword")
    public EmailResponseBody restorePassword(@Valid @RequestBody EmailDto emailDto, BindingResult bindingResult) {
        EmailResponseBody emailResponseBody = new EmailResponseBody();
        ////---///
//        Long countEmail = userDao.isRegisteredEmail(emailDto.getEmail());
//        if (countEmail > 0) {
//
//        }
        return new EmailResponseBody();
    }

    @PostMapping(value = "/user")
    public MessageResponse addUser(@Valid @RequestBody RegistrationUserDto registrationUserDto, BindingResult bindingResult) {

        MessageResponse response = new MessageResponse();

        if (registrationService.validateForm(registrationUserDto, bindingResult)) {
            User user = registrationService.userDtoToUser(registrationUserDto);
            user.setPassword(registrationService.encodePassword(user.getPassword()));
            userDao.addUser(user);
            mailService.sendRegistrationConfirmationEmail(user.getEmail());

            response.setStatus("SUCCESS");
//            response.setMessage("YOU HAVE BEEN SUCCESSFULLY REGISTERED...<br>" +
//                    "Please confirm your registration!\n" +
//                    "Your information has been sent successfully. In order to complete your registration, please click the confirmation link in the email that we have sent to you.\n" +
//                    "Please check, whether the email is in the junk folder of your email account, since confirmation mails with backlinks are sometimes classified as spam.");
        } else {
            response.setStatus("FAIL");
            response.setErrorList(bindingResult.getAllErrors());
        }

        return response;
    }

    @GetMapping(value = "/confirmRegistration")
    public ModelAndView processConfirmationEmailResponse(@RequestParam(value = "p", required = false) String decryptEmail) {
        ModelAndView modelAndView = new ModelAndView("activationFailed");
        try {
            String email = mailService.decryptEmail(decryptEmail);
            if (userDao.activateUser(email)) {
                modelAndView.setViewName("activationSuccessful");
            }
        } catch (RuntimeException e) {
            System.out.println("Мы так и не смогли расшифровать емайл или его не прислали");
        }
        return modelAndView;
    }


    //    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/userPanel", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView userPanelPage() {
        return new ModelAndView("userPanel");
    }

}
