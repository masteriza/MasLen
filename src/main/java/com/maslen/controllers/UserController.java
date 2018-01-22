package com.maslen.controllers;

import com.maslen.dao.interfaces.UserDao;
import com.maslen.services.interfaces.RegistrationService;
import com.maslen.services.interfaces.ResetPasswordService;
import com.maslen.services.interfaces.VerificationService;
import com.maslen.utils.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private static final String EMAIL_NOT_REGISTERED = "Email is not registered";
    private static final String EMAIL_NOT_CONFIRMED = "Your Email has not been activated. Check your email and follow the instructions";

    private final UserDao userDao;
    private final RegistrationService registrationService;
    private final ResetPasswordService resetPasswordService;
    private final VerificationService verificationService;
    private final MailService mailService;

    @Autowired
    public UserController(UserDao userDao,
                          RegistrationService registrationService,
                          ResetPasswordService resetPasswordService,
                          VerificationService verificationService,
                          MailService mailService) {
        this.userDao = userDao;
        this.registrationService = registrationService;
        this.resetPasswordService = resetPasswordService;
        this.verificationService = verificationService;
        this.mailService = mailService;
    }

//    @GetMapping(value = "/registration")
//    public ModelAndView registrationPage() {
//        return new ModelAndView("registration");
//    }
//
//    @GetMapping(value = "/registrationSuccess")
//    public ModelAndView registrationSuccessPage() {
//        return new ModelAndView("registrationSuccess");
//    }


//    @GetMapping(value = "/restorePassword")
////    public ModelAndView restorePasswordPage() {
////        return new ModelAndView("restorePassword");
////    }
////
////    @PostMapping(value = "/restorePassword")
////    public MessageResponseBody restorePassword(@Valid @RequestBody EmailDto emailDto, BindingResult bindingResult) {
////
////        MessageResponseBody messageResponseBody = new MessageResponseBody();
////        String email = emailDto.getEmail();
////
////        if (!verificationService.isRegisteredEmail(email)) {
////            bindingResult.rejectValue("email", "error.user.email.nonRegistered", EMAIL_NOT_REGISTERED);
////        } else if (!verificationService.isConfirmationEmail(email)) {
////            bindingResult.rejectValue("email", "error.user.email.nonConfirmed", EMAIL_NOT_CONFIRMED);
////            mailService.sendRegistrationConfirmationEmail(email);
////        }
////
////        if (bindingResult.hasErrors()) {
////            messageResponseBody.setErrorList(bindingResult.getAllErrors());
////            messageResponseBody.setStatus("FAIL");
////        } else {
////            User user = userDao.getUserByEmail(email);
////            UserActivity userActivity = UserActivity.builder()
////                    .user(user)
////                    .createDate(new Date())
////                    .endDate(LocalDateTime.now().plusDays(1))
////                    .action('R')
////                    .session(UUID.randomUUID().toString())
////                    .status('A').build();
////
////            userDao.addUserActivity(userActivity);
////            mailService.sendRestorePasswordEmail(email, user.getUserId(), userActivity);
////            messageResponseBody.setStatus("OK");
////        }
////
////        return messageResponseBody;
////    }
////
////    @GetMapping(value = "/restorePasswordStart")
////    public ModelAndView restorePasswordStartPage() {
////        return new ModelAndView("restorePasswordStart");
////    }
////
////    @GetMapping(value = "/resetPassword")
////    public ModelAndView resetPasswordPage() {
////        return new ModelAndView("resetPassword");
////    }
////
////    @PostMapping(value = "/resetPassword")
////    public MessageResponseBody processResetPassword(@Valid @RequestBody ResetPasswordDto resetPasswordDto, BindingResult bindingResult) {
////        MessageResponseBody response = new MessageResponseBody();
////
////        resetPasswordDto.setId(verificationService.decrypt(resetPasswordDto.getId()));
////
////        if (resetPasswordService.validateForm(resetPasswordDto, bindingResult)) {
////
////
////            response.setStatus("OK");
////        } else {
////            response.setStatus("FAIL");
////            response.setErrorList(bindingResult.getAllErrors());
////        }
////
////        return response;
////    }

//    @PostMapping(value = "/user")
//    public MessageResponseBody addUser(@Valid @RequestBody RegistrationUserDto registrationUserDto, BindingResult bindingResult) {
//
//        MessageResponseBody response = new MessageResponseBody();
//
//        if (registrationService.validateForm(registrationUserDto, bindingResult)) {
//            User user = registrationService.userDtoToUser(registrationUserDto);
//            user.setPassword(verificationService.encryptPassword(user.getPassword()));
//            userDao.addUser(user);
//            mailService.sendRegistrationConfirmationEmail(user.getEmail());
//
//            response.setStatus("OK");
//        } else {
//            response.setStatus("FAIL");
//            response.setErrorList(bindingResult.getAllErrors());
//        }
//
//        return response;
//    }

//    @GetMapping(value = "/confirmRegistration")
//    public ModelAndView processConfirmationEmail(@RequestParam(value = "p", required = false) String decryptEmail) {
//        ModelAndView modelAndView = new ModelAndView("activationFailed");
//        try {
//            String email = mailService.decryptEmail(decryptEmail);
//            if (userDao.activateUser(email)) {
//                modelAndView.setViewName("activationSuccessful");
//            }
//        } catch (RuntimeException e) {
//            System.out.println("Мы так и не смогли расшифровать емайл или его не прислали");
//        }
//        return modelAndView;
//    }


//    //    @PreAuthorize("hasRole('USER')")
//    @RequestMapping(value = "/userPanel", method = {RequestMethod.GET, RequestMethod.POST})
//    public ModelAndView userPanelPage() {
//        return new ModelAndView("userPanel");
//    }

}
