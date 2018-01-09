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
//        Long countEmail = userDao.isRegisteredEmail(emailDto.getEmail());
//        if (countEmail > 0) {
//
//        }


        return new EmailResponseBody();
    }


    @PostMapping(value = "/user")
    public MessageResponse addUser(@Valid @RequestBody RegistrationUserDto registrationUserDto, BindingResult bindingResult) {

        MessageResponse response = new MessageResponse();
        if (!bindingResult.hasErrors()) {
            if (registrationService.validateForm(registrationUserDto, bindingResult)) {
                User user = registrationService.userDtoToUser(registrationUserDto);
                user.setPassword(registrationService.encodePassword(user.getPassword()));
                userDao.addUser(user);
                mailService.sendRegistrationConfirmationEmail(user.getEmail());


                response.setStatus("SUCCESS");
            } else {
                response.setStatus("FAIL");
                response.setErrorList(bindingResult.getAllErrors());
            }
        }
        return response;
    }

    @GetMapping(value = "/confirmRegistration")
    public String processConfirmationEmailResponse(@RequestParam("param") String email) {
        String resultPage = "redirect:/successfulActivation";
        //if (userDao.activateUser(mailService.decryptEmail(email)) < 1) {
            resultPage = "redirect:/error";
        //}
        //todo:доделать нормальный ресспонс
        return resultPage;
    }


    //    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/userPanel", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView userPanelPage() {
        return new ModelAndView("userPanel");
    }

}
