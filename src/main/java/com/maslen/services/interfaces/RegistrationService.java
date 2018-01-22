package com.maslen.services.interfaces;

import com.maslen.models.RegistrationUserDto;
import com.maslen.models.User;
import org.springframework.validation.BindingResult;

public interface RegistrationService {
    BindingResult validateForm(RegistrationUserDto registrationUserDto, BindingResult bindingResult);

    User userDtoToUser(RegistrationUserDto registrationUserDto);
}
