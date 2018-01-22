package com.maslen.services;

import com.maslen.dao.interfaces.UserDao;
import com.maslen.models.ResetPasswordDto;
import com.maslen.services.interfaces.CommonService;
import com.maslen.services.interfaces.ResetPasswordService;
import com.maslen.services.interfaces.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

@Component
public class ResetPasswordServiceImpl implements ResetPasswordService {

    private static final String EMAIL_NOT_UNIQUE = "Email is not unique";
    private static final String PASSWORDS_NOT_MATCH = "Passwords do not match";

    private static final String RESPONSE_DATA_NOT_VALID = "Response data is not valid.";
    private static final String EMAIL_NOT_EXIST = "Email is not exist";

    private final UserDao userDao;
    private final BCryptPasswordEncoder encoder;
    private final VerificationService verificationService;
    private final CommonService commonService;

    @Autowired
    public ResetPasswordServiceImpl(UserDao userDao, BCryptPasswordEncoder encoder, VerificationService verificationService, CommonService commonService) {
        this.userDao = userDao;
        this.encoder = encoder;
        this.verificationService = verificationService;
        this.commonService = commonService;
    }

    @Override
    public BindingResult validateForm(ResetPasswordDto resetPasswordDto, BindingResult bindingResult) {


        try {
            String email = commonService.decrypt(resetPasswordDto.getId());
            resetPasswordDto.setId(email);
        } catch (RuntimeException e) {
            bindingResult.rejectValue("id", "error.data.response.notValid", RESPONSE_DATA_NOT_VALID);
        }

        if (!bindingResult.hasErrors()) {
            if (verificationService.isRegisteredEmail(resetPasswordDto.getId())) {

                if (!verificationService.isPasswordMatch(resetPasswordDto.getRawPassword(), resetPasswordDto.getRepeatRawPassword(), bindingResult)) {
                    bindingResult.rejectValue("rawPassword", "error.user.password.missMatch", PASSWORDS_NOT_MATCH);
                }

            } else {
                bindingResult.rejectValue("email", "error.user.email.notExist", EMAIL_NOT_EXIST);
            }


        }


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
        return bindingResult;
    }


}
