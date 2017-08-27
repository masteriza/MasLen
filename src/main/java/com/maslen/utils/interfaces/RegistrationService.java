package com.maslen.utils.interfaces;

import com.maslen.models.RegistrationUserDto;
import org.springframework.validation.BindingResult;

public interface RegistrationService {
    boolean validateForm(RegistrationUserDto registrationUserDto, BindingResult bindingResult);

    boolean isRegisteredEmail(String email);

    boolean isRegisteredPhone(String phone);

    boolean isPasswordMatch(String rawPassword, String repeatPassword, BindingResult bindingResult);

    String encodePassword(String rawPassword);


}
