package com.maslen.services;

import com.maslen.dao.interfaces.UserDao;
import com.maslen.models.ResetPasswordDto;
import com.maslen.services.interfaces.ResetPasswordService;
import com.maslen.services.interfaces.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

@Component
public class ResetPasswordServiceImpl implements ResetPasswordService {

    private static final String EMAIL_NOT_UNIQUE = "Email is not unique!";
    private static final String PASSWORDS_NOT_MATCH = "Passwords do not match!";
    private static final String PHONE_NOT_UNIQUE = "Phone is not unique!";

    private final UserDao userDao;
    private final BCryptPasswordEncoder encoder;
    private final VerificationService verificationService;


    @Autowired
    public ResetPasswordServiceImpl(UserDao userDao, BCryptPasswordEncoder encoder, VerificationService verificationService) {
        this.userDao = userDao;
        this.encoder = encoder;
        this.verificationService = verificationService;
    }

    @Override
    public boolean validateForm(ResetPasswordDto resetPasswordDto, BindingResult bindingResult) {
        boolean isValidData = true;
//        if (verificationService.isRegisteredEmail(resetPasswordDto.getEmail())) {
//            bindingResult.rejectValue("email", "error.user.email.nonUnique", EMAIL_NOT_UNIQUE);
//        }
//        if (!verificationService.isPasswordMatch(resetPasswordDto.getRawPassword(), resetPasswordDto.getRepeatRawPassword(), bindingResult)) {
//            bindingResult.rejectValue("rawPassword", "error.user.password.missMatch", PASSWORDS_NOT_MATCH);
//        }
//        if (verificationService.isRegisteredPhone(resetPasswordDto.getPhone())) {
//            bindingResult.rejectValue("phone", "error.user.phone.nonUnique", PHONE_NOT_UNIQUE);
//        }
//        if (bindingResult.hasErrors()) {
//            isValidData = false;
//        }
        return isValidData;
    }


}
