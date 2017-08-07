package com.maslen.utils;

import com.maslen.dao.Inf.UserDao;
import com.maslen.models.User;
import com.maslen.utils.interfaces.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

@Component
public class RegistrationServiceImpl implements RegistrationService {

    private static final String EMAIL_NOT_UNIQUE = "Email is not unique!";
    private static final String PASSWORDS_NOT_MATCH = "Passwords do not match!";
    private static final String PHONE_NOT_UNIQUE = "Phone is not unique!";

    private final UserDao userDao;
    private final BCryptPasswordEncoder encoder;

    @Autowired
    public RegistrationServiceImpl(UserDao userDao, BCryptPasswordEncoder encoder) {
        this.userDao = userDao;
        this.encoder = encoder;
    }


    @Override
    public boolean validateForm(User user, BindingResult bindingResult) {
        boolean isValidData = true;
        if (!isRegisteredEmail(user.getEmail())) {
            bindingResult.rejectValue("email", "error.user.email.nonUnique", EMAIL_NOT_UNIQUE);
        }
        if (!isPasswordMatch(user.getRawPassword(), user.getRepeatRawPassword(), bindingResult)) {
            bindingResult.rejectValue("rawPassword", "error.user.password.missMatch", PASSWORDS_NOT_MATCH);
        }
        if (!isRegisteredPhone(user.getPhone())) {
            bindingResult.rejectValue("phone", "error.user.phone.nonUnique", PHONE_NOT_UNIQUE);
        }
        if (bindingResult.hasErrors()) {
            isValidData = false;
        }
        return isValidData;
    }

    @Override
    public boolean isRegisteredEmail(String email) {
        return userDao.isRegisteredEmail(email) == 0;
    }

    @Override
    public boolean isPasswordMatch(String rawPassword, String repeatRawPassword, BindingResult bindingResult) {
//        if (!rawPassword.equals(repeatRawPassword)) {
//            bindingResult.rejectValue("rawPassword", "error.user.password.missMatch");
//        }
        return rawPassword.equals(repeatRawPassword);
    }


    @Override
    public boolean isRegisteredPhone(long phone) {
        return userDao.isRegisteredPhone(phone) == 0;
    }

    @Override
    public String encodePassword(String rawPassword) {
        return encoder.encode(rawPassword);
    }
}
