package com.maslen.utils.interfaces;

import com.maslen.models.User;
import org.springframework.validation.BindingResult;

public interface RegistrationService {
    boolean validateForm(User user, BindingResult bindingResult);

    boolean isRegisteredEmail(String email);

    boolean isRegisteredPhone(long phone);

    boolean isPasswordMatch(String rawPassword, String repeatPassword, BindingResult bindingResult);

    String encodePassword(String rawPassword);


}
