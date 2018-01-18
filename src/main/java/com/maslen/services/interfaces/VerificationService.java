package com.maslen.services.interfaces;

import org.springframework.validation.BindingResult;

public interface VerificationService {

    boolean isRegisteredEmail(String email);

    boolean isRegisteredPhone(String phone);

    boolean isPasswordMatch(String rawPassword, String repeatPassword, BindingResult bindingResult);

    String encodePassword(String rawPassword);

    String encode(String parameter);



}
