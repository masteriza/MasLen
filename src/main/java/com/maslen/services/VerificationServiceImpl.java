package com.maslen.services;

import com.maslen.dao.interfaces.UserDao;
import com.maslen.services.interfaces.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

@Component
public class VerificationServiceImpl implements VerificationService {
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

    @Override
    public String encode(String parameter) {
        return encoder.encode(parameter);
    }

    @Override
    public boolean isConfirmationEmail(String email) {
        return userDao.isConfirmationEmail(email);
    }


}
