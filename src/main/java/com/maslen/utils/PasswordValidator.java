package com.maslen.utils;

import com.maslen.models.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PasswordValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        if (!user.getRawPassword().equals(user.getRepeatRawPassword())) {
            errors.rejectValue("password", "customer.password.missMatch");
        }
    }
}
