package com.maslen.controllers;

import com.maslen.dao.interfaces.UserDao;
import com.maslen.models.EmailDto;
import com.maslen.models.MessageResponseBody;
import com.maslen.models.User;
import com.maslen.models.UserActivity;
import com.maslen.services.interfaces.VerificationService;
import com.maslen.utils.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@RestController
public class RestorePasswordController {

    private static final String EMAIL_NOT_REGISTERED = "Email is not registered";
    private static final String EMAIL_NOT_CONFIRMED = "Your Email has not been activated. Check your email and follow the instructions";

    private final UserDao userDao;
    private final VerificationService verificationService;
    private final MailService mailService;

    @Autowired
    public RestorePasswordController(UserDao userDao,
                                     VerificationService verificationService,
                                     MailService mailService) {
        this.userDao = userDao;
        this.verificationService = verificationService;
        this.mailService = mailService;
    }

    @GetMapping(value = "/restorePassword")
    public ModelAndView restorePasswordPage() {
        return new ModelAndView("restorePassword");
    }

    @PostMapping(value = "/restorePassword")
    public MessageResponseBody restorePassword(@Valid @RequestBody EmailDto emailDto, BindingResult bindingResult) {

        MessageResponseBody messageResponseBody = new MessageResponseBody();
        String email = emailDto.getEmail();

        if (!bindingResult.hasErrors()) {
            if (!verificationService.isRegisteredEmail(email)) {
                bindingResult.rejectValue("email", "error.user.email.nonRegistered", EMAIL_NOT_REGISTERED);
            } else if (!verificationService.isConfirmationEmail(email)) {
                bindingResult.rejectValue("email", "error.user.email.nonConfirmed", EMAIL_NOT_CONFIRMED);
                mailService.sendRegistrationConfirmationEmail(email);
            }
        }

        if (bindingResult.hasErrors()) {
            messageResponseBody.setErrorList(bindingResult.getAllErrors());
            messageResponseBody.setStatus("FAIL");
        } else {
            User user = userDao.getUserByEmail(email);
            UserActivity userActivity = UserActivity.builder()
                    .user(user)
                    .createDate(new Date())
                    .endDate(LocalDateTime.now().plusDays(1))
                    .action('R')
                    .session(UUID.randomUUID().toString())
                    .status('A').build();

            userDao.addUserActivity(userActivity);
            mailService.sendRestorePasswordEmail(email, user.getUserId(), userActivity);
            messageResponseBody.setStatus("OK");
        }

        return messageResponseBody;
    }

    @GetMapping(value = "/restorePasswordStart")
    public ModelAndView restorePasswordStartPage() {
        return new ModelAndView("restorePasswordStart");
    }

    @GetMapping(value = "/restorePasswordSuccess")
    public ModelAndView restorePasswordSuccessPage() {
        return new ModelAndView("restorePasswordSuccess");
    }
}
