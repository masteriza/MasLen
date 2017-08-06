package com.maslen.utils;

import com.maslen.dao.Inf.UserDao;
import com.maslen.utils.interfaces.UserValidator;
import org.springframework.stereotype.Component;

@Component
public class UserValidatorImpl implements UserValidator {
    private final UserDao userDao;

    public UserValidatorImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public boolean isRegisteredEmail(String email) {

        return userDao.isRegisteredEmail(email) == 0;
    }

    @Override
    public boolean isRegisteredPhone(long phone) {
        return userDao.isRegisteredPhone(phone) == 0;
    }

    @Override
    public boolean isPasswordMatch(String rawPassword, String repeatRawPassword) {
        if (!rawPassword.equals(repeatRawPassword)) {
            errors.rejectValue("password", "user.password.missMatch");
        }
        return false;
    }
}
