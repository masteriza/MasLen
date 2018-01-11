package com.maslen.controllers;

//import com.maslen.dao.interfaces.UserDao;

import com.maslen.dao.interfaces.UserDao;
import com.maslen.models.EmailDto;
import com.maslen.models.MessageResponseBody;
import com.maslen.models.RegistrationUserDto;
import com.maslen.models.User;
import com.maslen.utils.MailService;
import com.maslen.utils.interfaces.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Date;
import java.util.UUID;

@RestController
public class UserController {

    private static final String EMAIL_NOT_REGISTERED = "Email is not registered";
    private static final String EMAIL_NOT_CONFIRMED = "Your Email has not been activated. Check your email and follow the instructions";

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
//    public EmailResponseBody restorePassword(@Valid @RequestBody EmailDto emailDto, BindingResult bindingResult) {
    public MessageResponseBody restorePassword(@Valid @RequestBody EmailDto emailDto, BindingResult bindingResult) {

        MessageResponseBody messageResponseBody = new MessageResponseBody();

        String email = emailDto.getEmail();

        if (!userDao.isRegisteredEmail(email)) {
            bindingResult.rejectValue("email", "error.user.email.nonRegistered", EMAIL_NOT_REGISTERED);

        }

        if (!userDao.isConfirmationEmail(email)) {
            bindingResult.rejectValue("email", "error.user.email.nonConfirmed", EMAIL_NOT_CONFIRMED);
            mailService.sendRegistrationConfirmationEmail(email);
        }

        if (bindingResult.hasErrors()) {
            messageResponseBody.setErrorList(bindingResult.getAllErrors());
        } else {
            userDao.addUserActivity(new Date(), "P", UUID.randomUUID().toString(), "A");
            mailService.sendRestorePasswordEmail(email);
        }


        return messageResponseBody;
    }

    @PostMapping(value = "/user")
    public MessageResponseBody addUser(@Valid @RequestBody RegistrationUserDto registrationUserDto, BindingResult bindingResult) {

        MessageResponseBody response = new MessageResponseBody();

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
