package com.maslen.services;

import com.maslen.dao.interfaces.UserDao;
import com.maslen.services.interfaces.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

@Component
public class VerificationServiceImpl implements VerificationService {

    private static final String EMAIL_NOT_UNIQUE = "Email is not unique!";
    private static final String PASSWORDS_NOT_MATCH = "Passwords do not match!";
    private static final String PHONE_NOT_UNIQUE = "Phone is not unique!";

    private final UserDao userDao;
    private final BCryptPasswordEncoder encoder;

    @Autowired
    public VerificationServiceImpl(UserDao userDao, BCryptPasswordEncoder encoder) {
        this.userDao = userDao;
        this.encoder = encoder;
    }


    @Override
    public boolean isRegisteredEmail(String email) {
        return userDao.isRegisteredEmail(email);
    }

    @Override
    public boolean isPasswordMatch(String rawPassword, String repeatRawPassword, BindingResult bindingResult) {
        return rawPassword.equals(repeatRawPassword);
    }

    @Override
    public boolean isRegisteredPhone(String phone) {
        return userDao.isRegisteredPhone(phone);
    }

    @Override
    public String encodePassword(String rawPassword) {
        return encoder.encode(rawPassword);
    }


}
