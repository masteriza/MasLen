package com.maslen.controllers;

import com.maslen.models.MessageResponseBody;
import com.maslen.models.ResetPasswordDto;
import com.maslen.services.interfaces.CommonService;
import com.maslen.services.interfaces.ResetPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@RestController
public class ResetPasswordController {

    private static final String RESPONSE_DATA_NOT_VALID = "Email is not registered";
    private static final String EMAIL_NOT_CONFIRMED = "Your Email has not been activated. Check your email and follow the instructions";

    private final ResetPasswordService resetPasswordService;
    private final CommonService commonService;

    @Autowired
    public ResetPasswordController(ResetPasswordService resetPasswordService, CommonService commonService) {
        this.resetPasswordService = resetPasswordService;
        this.commonService = commonService;
    }

    @GetMapping(value = "/resetPassword")
    public ModelAndView resetPasswordPage() {
        return new ModelAndView("resetPassword");
    }

    @PostMapping(value = "/resetPassword")
    public MessageResponseBody processResetPassword(@Valid @RequestBody ResetPasswordDto resetPasswordDto, BindingResult bindingResult) {
        MessageResponseBody response = new MessageResponseBody();

//        try {
//            String email = commonService.decrypt(resetPasswordDto.getId());
//            resetPasswordDto.setId(email);
//        } catch (RuntimeException e) {
//            bindingResult.rejectValue("email", "error.data.response.notValid", RESPONSE_DATA_NOT_VALID);
//        }


        if (!resetPasswordService.validateForm(resetPasswordDto, bindingResult).hasErrors()) {
            response.setStatus("OK");
        } else {
            response.setStatus("FAIL");
            response.setErrorList(bindingResult.getAllErrors());
        }

        return response;
    }

    @GetMapping(value = "/resetPasswordSuccess")
    public ModelAndView resetPasswordSuccessPage() {
        return new ModelAndView("resetPasswordSuccess");
    }


}
